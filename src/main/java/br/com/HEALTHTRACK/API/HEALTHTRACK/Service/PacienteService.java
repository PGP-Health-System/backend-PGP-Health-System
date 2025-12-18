package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar.HistoricoFamiliarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.PacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    @Autowired
    private PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public String cadastrarPaciente(@Valid PacienteCadastroDTO dto) {

        if (pacienteRepository.existsByCpf(dto.cpf()))
            throw new CpfDuplicado("CPF já cadastrado");

        if (pacienteRepository.existsByEmail(dto.email()))
            throw new EmailDuplicado("Email já cadastrado");

        if (dto.dataNascimento().isAfter(LocalDate.now()))
            throw new DataNascimentoInvalida("Data de nascimento futura");

        Paciente paciente = pacienteMapper.convertePacienteEntidadeCadastro(dto);
        paciente.setStatusPaciente(StatusPaciente.ATIVO);
        paciente.setDataCadastro(LocalDate.now());

        pacienteRepository.save(paciente);

        return "Paciente cadastrado com sucesso! " +
                pacienteMapper.converterPacienteDtoDetalhes(paciente).toString();
    }

    public String atualizarPaciente(Long id, PacienteAtualizacaoDTO dto) {

        Paciente paciente = buscarPaciente(id);

        if (dto.nome() != null) paciente.setNome(dto.nome());
        if (dto.telefone() != null) paciente.setTelefone(dto.telefone());
        if (dto.sexo() != null) paciente.setSexo(dto.sexo());
        if (dto.estadoCivil() != null) paciente.setEstadoCivil(dto.estadoCivil());
        if (dto.nomeMae() != null) paciente.setNomeMae(dto.nomeMae());
        if (dto.numeroSus() != null) paciente.setNumeroSus(dto.numeroSus());

        pacienteRepository.save(paciente);

        return "Paciente atualizado com sucesso! " +
                pacienteMapper.converterPacienteDtoDetalhes(paciente).toString();
    }

    public String desativarPaciente(Long id) {
        Paciente paciente = buscarPaciente(id);

        if (paciente.getStatusPaciente() != StatusPaciente.ATIVO)
            throw new ErroDesativarPaciente("Paciente não está ativo");

        paciente.setStatusPaciente(StatusPaciente.INATIVO);
        pacienteRepository.save(paciente);

        return "Paciente desativado com sucesso! " +
        pacienteMapper.converterPacienteDtoDetalhes(paciente).toString();
    }

    public String ativarPaciente(Long id) {
        Paciente paciente = buscarPaciente(id);

        if (paciente.getStatusPaciente() == StatusPaciente.ATIVO)
            throw new ErroAtivarPaciente("Paciente já está ativo");

        paciente.setStatusPaciente(StatusPaciente.ATIVO);
        pacienteRepository.save(paciente);

        return "Paciente ativado com sucesso! " +
                pacienteMapper.converterPacienteDtoDetalhes(paciente).toString();
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

    public void adicionarDoenca(Long id, DoencaDTO doenca){
        // Implementar Modulo Doenca.
    }

    public void atualizarHistoricoFamiliar(Long id, HistoricoFamiliarDTO historicoFamiliar){
        // Implementar Modulo Historico Familiar.
    }

    public void atualizarResponsavel(Long id, Long idResponsavel){
        // Implementar Modulo Responsavel.
    }

    private Paciente buscarPaciente(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new PacienteNaoLocalizado("Paciente não encontrado"));
    }
}

