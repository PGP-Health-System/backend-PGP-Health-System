package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteAtualizarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlergiaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Alergia.AlergiaDuplicada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Alergia.AlergiaNaoLocalizada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.AlergiaPaciente.AlergiaPacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlergiaPacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlergiaRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AlergiaPacienteService {

    private final AlergiaPacienteRepository alergiaPacienteRepository;
    private final PacienteRepository pacienteRepository;
    private final AlergiaRepository alergiaRepository;
    private final AlergiaPacienteMapper alergiaPacienteMapper;

    public AlergiaPacienteService(AlergiaPacienteRepository alergiaPacienteRepository, PacienteRepository pacienteRepository,
                                  AlergiaRepository alergiaRepository, AlergiaPacienteMapper alergiaPacienteMapper) {
        this.alergiaPacienteRepository = alergiaPacienteRepository;
        this.alergiaRepository = alergiaRepository;
        this.pacienteRepository = pacienteRepository;
        this.alergiaPacienteMapper = alergiaPacienteMapper;
    }

    public AlergiaPacienteDetalheDTO cadastrarAlergiaPaciente(AlergiaPacienteCadastroDTO dto){

        Paciente pacienteLocalizado = buscarPacienteExiste(dto.cpfPaciente());
        Alergia alergiaLocalizada = buscarAlergiaExiste(dto.nomeAlergia());
        if(alergiaDuplicadaPaciente(dto.cpfPaciente(), dto.nomeAlergia())){
            throw new AlergiaDuplicada("Alergia ja cadastrada para este paciente");
        }

        AlergiaPaciente alergiaPacienteConvertida = alergiaPacienteMapper.toEntityAlergiaPacienteCadastro(dto, pacienteLocalizado, alergiaLocalizada);
        alergiaPacienteRepository.save(alergiaPacienteConvertida);

        return alergiaPacienteMapper.toEntityDetalheDTO(alergiaPacienteConvertida);
    }

    public List<AlergiaPacienteDetalheDTO> listarAlergiaPaciente(String cpf){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        List<AlergiaPaciente> alergiasPaciente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId());
        return alergiasPaciente.stream()
                .map(alergiaPacienteMapper::toEntityDetalheDTO)
                .toList();
    }

    public AlergiaPacienteDetalheDTO buscarAlergiaPacienteEspecifica(String cpf, String nomeAlergia){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        Alergia alergiaLocalizada = buscarAlergiaExiste(nomeAlergia);

        AlergiaPaciente alergiaPaciente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId()).stream()
                .filter(ap -> ap.getAlergia().getNome().equals(alergiaLocalizada.getNome()))
                .findFirst()
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada para este paciente"));

        return alergiaPacienteMapper.toEntityDetalheDTO(alergiaPaciente);
    }

    public AlergiaPacienteDetalheDTO atualizarAlergiaPaciente(String cpf, String nomeAlergia, AlergiaPacienteAtualizarDTO dto){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        Alergia alergiaLocalizada = buscarAlergiaExiste(nomeAlergia);

        AlergiaPaciente alergiaPacienteExistente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId()).stream()
                .filter(ap -> ap.getAlergia().getNome().equals(alergiaLocalizada.getNome()))
                .findFirst()
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada para este paciente"));

        if(dto.confirmada() != alergiaPacienteExistente.isConfirmada()){
            alergiaPacienteExistente.setConfirmada(dto.confirmada());
        }
        if(dto.observacao() != null){
            alergiaPacienteExistente.setObservacao(dto.observacao());
        }
        alergiaPacienteExistente.setDataAtualizacao(LocalDate.now());

        AlergiaPaciente alergiaPacienteAtualizada = alergiaPacienteMapper.toEntityAlergiaPacienteAtualizar(alergiaPacienteExistente);
        alergiaPacienteRepository.save(alergiaPacienteAtualizada);

        return alergiaPacienteMapper.toEntityDetalheDTO(alergiaPacienteAtualizada);
    }

    public AlergiaPacienteDetalheDTO ativarAlergiaPacinte(String cpf, String nomeAlergia){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        Alergia alergiaLocalizada = buscarAlergiaExiste(nomeAlergia);

        AlergiaPaciente alergiaPacienteExistente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId()).stream()
                .filter(ap -> ap.getAlergia().getNome().equals(alergiaLocalizada.getNome()))
                .findFirst()
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada para este paciente"));

        alergiaPacienteExistente.setConfirmada(true);
        alergiaPacienteExistente.setDataAtualizacao(LocalDate.now());

        AlergiaPaciente alergiaPacienteAtualizada = alergiaPacienteMapper.toEntityAlergiaPacienteAtualizar(alergiaPacienteExistente);
        alergiaPacienteRepository.save(alergiaPacienteAtualizada);

        return alergiaPacienteMapper.toEntityDetalheDTO(alergiaPacienteAtualizada);
    }

    public AlergiaPacienteDetalheDTO desativarAlergiaPaciente(String cpf, String nomeAlergia){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        Alergia alergiaLocalizada = buscarAlergiaExiste(nomeAlergia);

        AlergiaPaciente alergiaPacienteExistente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId()).stream()
                .filter(ap -> ap.getAlergia().getNome().equals(alergiaLocalizada.getNome()))
                .findFirst()
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada para este paciente"));

        alergiaPacienteExistente.setConfirmada(false);
        alergiaPacienteExistente.setDataAtualizacao(LocalDate.now());

        AlergiaPaciente alergiaPacienteAtualizada = alergiaPacienteMapper.toEntityAlergiaPacienteAtualizar(alergiaPacienteExistente);
        alergiaPacienteRepository.save(alergiaPacienteAtualizada);

        return alergiaPacienteMapper.toEntityDetalheDTO(alergiaPacienteAtualizada);
    }

    public List<AlergiaPacienteDetalheDTO> listarAlergiasAtivasPaciente(String cpf){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        List<AlergiaPaciente> alergiasAtivas = alergiaPacienteRepository.findByPacienteIdAndConfirmadaTrue(pacienteLocalizado.getId());
        return alergiasAtivas.stream()
                .map(alergiaPacienteMapper::toEntityDetalheDTO)
                .toList();
    }

    public List<AlergiaPacienteDetalheDTO> listarAlergiasConfirmadas(String cpf){
        Paciente pacienteLocalizado = buscarPacienteExiste(cpf);
        List<AlergiaPaciente> alergiasPaciente = alergiaPacienteRepository.findAllByPacienteId(pacienteLocalizado.getId()).stream()
                .filter(AlergiaPaciente::isConfirmada)
                .toList();
        return alergiasPaciente.stream()
                .map(alergiaPacienteMapper::toEntityDetalheDTO)
                .toList();
    }

    private Paciente buscarPacienteExiste(String cpf){
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente nao encontrado"));
    }

    private Alergia buscarAlergiaExiste(String nomeAlergia){
        return alergiaRepository.findByNome(nomeAlergia)
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada"));
    }

    private boolean alergiaDuplicadaPaciente(String cpfPaciente, String nomeAlergia){
        return alergiaPacienteRepository.existsByPacienteCpfAndAlergiaNome(cpfPaciente, nomeAlergia);
    }
}
