package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.AdicionaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Profissional.ProfissionalMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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


        ProfissionalSaude profissionalSaude1 = profissionalMapper.ProfissionalSaudeParaEntidade(profissionalDTO);
        profissionalSaude1.setPacientes(pacientes);

        if (!pacientes.isEmpty()) {
            profissionalSaude1.setPacientes(pacientes);
        } else {
            profissionalSaude1.setPacientes(Collections.emptyList());
        }

        profissionalSaudeRepository.save(profissionalSaude1);

        //Aqui seta os profissionais dos pacientes que foram registrados primeiro
        pacientes.forEach(p -> p.setProfissionalSaude(profissionalSaude1));
        pacienteRepository.saveAll(pacientes);

        return profissionalMapper.EntidadeParaDetalhe(profissionalSaude1);

    }

    @Transactional
    public void adicionarPacientes(AdicionaDTO adicionaDTO) {

        List<Paciente> pacientes = pacienteRepository.findByCpfIn(adicionaDTO.cpf());


        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findByEmail(adicionaDTO.email())
                .orElseThrow(() -> new EmailNaoEncontrado("Profissional não encontrado, email inválido"));


        pacientes.forEach(p -> p.setProfissionalSaude(profissionalSaude));


        pacienteRepository.saveAll(pacientes);
        pacienteRepository.flush();
        System.out.println("Pacientes encontrados: " + pacientes);
    }

    //getByName pega todos os pacientes por nome.
    public List<Paciente> mostrarPacientes(String nome) {
      return pacienteRepository.getByName(nome);
    }

    public void deletarPaciente(String nome, String email){

        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNaoEncontrado("Profissional não encontrado pelo email"));

        List<Paciente> pacientes = pacienteRepository.findByProfissionalSaudeId(profissionalSaude.getId());
        pacientes.stream()
                .filter(p -> p.getNome().equals(nome))
                .forEach(p -> p.setProfissionalSaude(null));

        pacienteRepository.saveAll(pacientes);
    }


}
