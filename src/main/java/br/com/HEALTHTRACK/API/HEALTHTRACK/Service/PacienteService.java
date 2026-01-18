package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar.HistoricoFamiliarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Paciente.PacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper, ProfissionalSaudeRepository profissionalSaudeRepository) {
        this.pacienteMapper = pacienteMapper;
        this.pacienteRepository = pacienteRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
    }

    public PacienteDetalhesDTO cadastrarPaciente(@Valid PacienteCadastroDTO dto) {

        if (pacienteRepository.existsByCpf(dto.cpf()))
            throw new CpfDuplicado("CPF já cadastrado");

        if (pacienteRepository.existsByEmail(dto.email()))
            throw new EmailDuplicado("Email já cadastrado");

        Paciente paciente = pacienteMapper.convertePacienteEntidadeCadastro(dto);
        paciente.setDataCadastro(LocalDate.now());

        pacienteRepository.save(paciente);

        return pacienteMapper.converterPacienteDtoDetalhes(paciente);
    }

    public PacienteDetalhesDTO atualizarPaciente(Long id, PacienteAtualizacaoDTO dto) {

        Paciente paciente = buscarPaciente(id);

        if (dto.nome() != null) paciente.setNome(dto.nome());
        if (dto.telefone() != null) paciente.setTelefone(dto.telefone());
        if (dto.sexo() != null) paciente.setSexo(dto.sexo());
        if (dto.estadoCivil() != null) paciente.setEstadoCivil(dto.estadoCivil());
        if (dto.nomeMae() != null) paciente.setNomeMae(dto.nomeMae());
        if (dto.numeroSus() != null) paciente.setNumeroSus(dto.numeroSus());

        pacienteRepository.save(paciente);

        return pacienteMapper.converterPacienteDtoDetalhes(paciente);
    }

    public PacienteDetalhesDTO desativarPaciente(Long id) {
        Paciente paciente = buscarPaciente(id);

        if (paciente.getStatusPaciente() != StatusPaciente.ATIVO)
            throw new ErroDesativarPaciente("Paciente não está ativo");

        paciente.setStatusPaciente(StatusPaciente.INATIVO);
        pacienteRepository.save(paciente);

        return pacienteMapper.converterPacienteDtoDetalhes(paciente);
    }

    public PacienteDetalhesDTO ativarPaciente(Long id) {
        Paciente paciente = buscarPaciente(id);

        if (paciente.getStatusPaciente() == StatusPaciente.ATIVO)
            throw new ErroAtivarPaciente("Paciente já está ativo");

        paciente.setStatusPaciente(StatusPaciente.ATIVO);
        pacienteRepository.save(paciente);

        return pacienteMapper.converterPacienteDtoDetalhes(paciente);
    }

    public PacienteDetalhesDTO buscarDetalhes(Long id) {
        return pacienteMapper.converterPacienteDtoDetalhes(buscarPaciente(id));
    }

    public List<PacienteResumoDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::converterPacienteResumoDto)
                .toList();
    }

    public List<PacienteResumoDTO> listarTodosAtivos(){
        return pacienteRepository.findAll()
                .stream()
                .filter(paciente -> paciente.getStatusPaciente() == StatusPaciente.ATIVO)
                .map(pacienteMapper::converterPacienteResumoDto)
                .toList();
    }

    public List<PacienteResumoDTO> listarTodosInativos(){
        return pacienteRepository.findAll()
                .stream()
                .filter(paciente -> paciente.getStatusPaciente() == StatusPaciente.INATIVO)
                .map(pacienteMapper::converterPacienteResumoDto)
                .toList();
    }

    private Paciente buscarPaciente(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new PacienteNaoLocalizado("Paciente não encontrado"));
    }

    public Paciente buscarPacientePorCpf(String cpf){
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() ->
                        new PacienteNaoLocalizado("Paciente não localizado com esse cpf"));
    }
}

