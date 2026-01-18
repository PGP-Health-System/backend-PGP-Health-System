package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaPacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SintomaService {

    @Autowired
    private SintomaRepository sintomaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    @Autowired
    private DoencaPacienteRepository doencaPacienteRepository;


    public SintomaRepository getSintomaRepository() {
        return sintomaRepository;
    }


    public DetalheSintomaDTO registarSintoma(SintomaDTO sintomaDTO) {
        ProfissionalSaude profissionalSaude =  profissionalSaudeRepository.findByEmail(sintomaDTO.emailProfissional())
                .orElseThrow(() -> new EmailNaoEncontrado("Profisisonal não encontrado com esse email."));

        Paciente paciente = pacienteRepository.findByCpf(sintomaDTO.pacienteCpf())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não localizado por esse cpf"));


    }


}
