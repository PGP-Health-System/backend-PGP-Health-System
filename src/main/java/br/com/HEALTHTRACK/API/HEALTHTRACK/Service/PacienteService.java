package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.PacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepositoryOficial;
    @Autowired
    PacienteMapper pacienteMapper;

    public void cadastrarPaciente(@Valid PacienteCadastroDTO pacienteDTO){
        try{
            if(pacienteRepositoryOficial.findByCpf(pacienteDTO.cpf()) != null){
                // Erro CPF Throw new Excpetion
            }

            if(pacienteRepositoryOficial.findByEmail(pacienteDTO.email()) != null){
                // Erro EMAIL Throw new Exception
            }

            if(pacienteDTO.dataNascimento().isBefore(LocalDate.now())){
                // Erro Data Nascimento Futura Throw new Excpetion
            }

            Paciente pacienteConvertido = pacienteMapper.convertePacienteEntidadeCadastro(pacienteDTO);
            pacienteRepositoryOficial.save(pacienteConvertido);
        }catch(Exception e){

        }
    }
}
