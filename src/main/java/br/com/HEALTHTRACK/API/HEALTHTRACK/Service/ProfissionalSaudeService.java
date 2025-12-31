package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Profissional.ProfissionalMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalSaudeService {

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    public ProfissionalSaudeRepository getProfissionalSaudeRepository() {
        return profissionalSaudeRepository;
    }


    public ProfissionalDetalhe cadastrar(ProfissionalDTO profissionalDTO) {

        List<Paciente> pacientes = pacienteRepository.findByCpfIn(profissionalDTO.cpfs());

        if (pacientes.isEmpty()) {
            throw new PacienteNaoLocalizado("Pacientes não foram lozalidados, cpf incorreto");
        }

        ProfissionalSaude profissionalSaude1 = profissionalMapper.ProfissionalSaudeParaEntidade(profissionalDTO);
        profissionalSaude1.setPacientes(pacientes);

        profissionalSaudeRepository.save(profissionalSaude1);

        //Aqui seta os profissionais dos pacientes que foram registrados primeiro
        pacientes.forEach(p -> p.setProfissionalSaude(profissionalSaude1));
        pacienteRepository.saveAll(pacientes);

        return profissionalMapper.EntidadeParaDetalhe(profissionalSaude1);

    }


}
