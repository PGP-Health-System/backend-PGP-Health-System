package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlertaSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.AlertaSaude.AlertaSaudeNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.AlertaSaude.AlertaSaudeMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlertaSaudeRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaSaudeService {

    private final AlertaSaudeRepository alertaSaudeRepository;
    private final PacienteRepository pacienteRepository;
    private final AlertaSaudeMapper alertaSaudeMapper;

    public AlertaSaudeService(AlertaSaudeRepository alertaSaudeRepository, PacienteRepository pacienteRepository, AlertaSaudeMapper alertaSaudeMapper) {
        this.alertaSaudeRepository = alertaSaudeRepository;
        this.pacienteRepository = pacienteRepository;
        this.alertaSaudeMapper = alertaSaudeMapper;
    }

    public AlertaSaudeDetalheDTO cadastrarAlerta(AlertaSaudeCadastroDTO dto){
        Optional<Paciente> pacienteLocalizado = pacienteRepository.findByCpf(dto.pacienteCpf());

        if(pacienteLocalizado.isEmpty()){
            throw new PacienteNaoLocalizado("Paciente com CPF " + dto.pacienteCpf() + " não encontrado.");
        }

        AlertaSaude alertaSaude = alertaSaudeMapper.toEntity(dto);
        alertaSaude.setPaciente(pacienteLocalizado.get());
        AlertaSaude alertaSalvo = alertaSaudeRepository.save(alertaSaude);

        return alertaSaudeMapper.toDetalheDTO(alertaSalvo);
    }

    public AlertaSaudeDetalheDTO buscarAlertaPorId(Long id){
        AlertaSaude alertaSaude = alertaSaudeRepository.findById(id)
                .orElseThrow(() -> new AlertaSaudeNaoLocalizado("Alerta de saúde com ID " + id + " não encontrado."));

        return alertaSaudeMapper.toDetalheDTO(alertaSaude);
    }

    public List<AlertaSaude> buscarAlertaPorCpf(String cpf){

        Paciente pacienteLocalizado = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente com CPF " + cpf + " não encontrado."));

        List<AlertaSaude> alertaSaude = alertaSaudeRepository.findByPaciente_Cpf(pacienteLocalizado.getCpf()).stream().toList();

        if(alertaSaude.isEmpty()){
            throw new AlertaSaudeNaoLocalizado("Nenhum alerta de saúde encontrado para o CPF " + cpf + ".");
        }

        return alertaSaude;
    }

    public List<AlertaSaudeDetalheDTO> listarAtivos() {
        List<AlertaSaude> alertasAtivos = alertaSaudeRepository.findAll().stream()
                .filter(alerta -> !alerta.isResolvido())
                .toList();

        return alertasAtivos.stream()
                .map(alertaSaudeMapper::toDetalheDTO)
                .toList();
    }

    public AlertaSaudeDetalheDTO alterarResolvido(Long id){
        AlertaSaude alertaSaude = alertaSaudeRepository.findById(id)
                .orElseThrow(() -> new AlertaSaudeNaoLocalizado("Alerta de saúde com ID " + id + " não encontrado."));

        alertaSaude.setResolvido(true);
        alertaSaude.setResolvidoEm(java.time.LocalDateTime.now());

        AlertaSaude alertaAtualizado = alertaSaudeRepository.save(alertaSaude);

        return alertaSaudeMapper.toDetalheDTO(alertaAtualizado);
    }
}
