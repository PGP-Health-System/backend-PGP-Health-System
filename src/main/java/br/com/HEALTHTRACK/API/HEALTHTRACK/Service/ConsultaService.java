package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDto;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepositoryOficial;

    @Autowired
    private RegistroMedicacaoRepository registroMedicacaoRepository;

    @Autowired
    ProfissionalSaudeRepository profissionalSaudeRepository;


    public ConsultaRepository getConsultaRepository() {
        return consultaRepository;
    }


    public void criarConsulta(ConsultaDTO consultaDTO){


        Paciente paciente = pacienteRepositoryOficial.findByCpf(consultaDTO.cpf())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        //Coming soon
    }

}
