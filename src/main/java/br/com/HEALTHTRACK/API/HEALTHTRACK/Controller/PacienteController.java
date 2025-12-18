package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.PacienteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    public PacienteService getPacienteService() {
        return pacienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPaciente(@RequestBody @Valid PacienteCadastroDTO pacienteDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrarPaciente(pacienteDTO));
        }catch (ErroCadastrarPaciente e){
            throw new ErroCadastrarPaciente("Houve um erro ao cadastrar o paciente, tente novamente: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody @Valid PacienteAtualizacaoDTO dto
    ) {
        try {
            return ResponseEntity.ok(pacienteService.atualizarPaciente(id, dto));
        } catch (ErroAtualizarPaciente e) {
            throw new ErroAtualizarPaciente(
                    "Houve um erro ao atualizar o paciente, tente novamente: " + e.getMessage()
            );
        }
    }


    @PutMapping("/desativar/{id}")
    public ResponseEntity<String> desativarPaciente(@PathVariable @Valid Long id){
        try{
            return ResponseEntity.status(200).body(pacienteService.desativarPaciente(id));
        }catch (ErroDesativarPaciente e){
            throw new ErroDesativarPaciente("Houve um erro ao desativar o paciente, tente novamente: " + e.getMessage());
        }
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<String> ativarPaciente(@PathVariable @Valid Long id){
        try{
            return ResponseEntity.status(200).body(pacienteService.ativarPaciente(id));
        }catch (ErroAtivarPaciente e){
            throw new ErroAtivarPaciente("Houve um erro ao desativar o paciente, tente novamente: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDetalhesDTO> buscarPacienteId(@PathVariable @Valid Long id){
        try{
            return ResponseEntity.status(200).body(pacienteService.buscarDetalhes(id));
        }catch (ErroAoBuscarPaciente e){
            throw new ErroAoBuscarPaciente("Houve um erro ao buscar o paciente " + id + ", tente novamente: " + e.getMessage());
        }
    }

    @GetMapping("/listar/todos")
    public ResponseEntity<List<PacienteResumoDTO>> listarTodosPacientes(){
        try{
            return ResponseEntity.status(200).body(pacienteService.listarTodos());
        }catch (ErroAoBuscarPaciente e){
            throw new ErroAoBuscarPaciente("Houve um erro ao listar os pacientes, tente novamente: " + e.getMessage());
        }
    }
}
