package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Consulta;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.RegistroMedicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Consulta.ConsultaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepositoryOficial;

    @Autowired
    private RegistroMedicacaoRepository registroMedicacaoRepository;

    @Autowired
    private ConsultaMapper consultaMapper;
    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    public ConsultaRepository getConsultaRepository() {
        return consultaRepository;
    }


    public void criarConsulta(ConsultaDTO consultaDTO){


        Paciente paciente = pacienteRepositoryOficial.findByCpf(consultaDTO.cpf())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));


        ProfissionalSaude profissional = profissionalSaudeRepository
                .findByEmail(consultaDTO.medicoEmail())
                .orElseThrow(() -> new EmailNaoEncontrado(
                        "Profissional de saúde não encontrado pelo email"
                ));

        List<RegistroMedicacao> registros =
                registroMedicacaoRepository.findAllById(consultaDTO.registrosMedicacaoIds());

        if (registros.size() != consultaDTO.registrosMedicacaoIds().size()) {
            throw new RuntimeException("Um ou mais registros de medicação não encontrados");
        }

        Consulta consulta = consultaMapper.toEntity(consultaDTO);
        consulta.setPaciente(paciente);
        consulta.setProfissionalSaude(profissional);
        registros.forEach(r -> r.setConsulta(consulta));

        consultaRepository.save(consulta);

        ConsultaDetalhe consultaDetalhe = consultaMapper.toDetail(consulta);

    }

}
