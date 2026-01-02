package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.AdicionaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional-saude")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ProfissionalSaudeController {

    @Autowired
    private ProfissionalSaudeService profissionalSaudeService;

    public ProfissionalSaudeService getProfissionalSaudeService() {
        return profissionalSaudeService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<ProfissionalDetalhe> cadastrar(@RequestBody ProfissionalDTO profissionalDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSaudeService.cadastrar(profissionalDTO));
    }

    @RequestMapping(value = "/adicionar-pacientes", method = RequestMethod.PATCH)
    public ResponseEntity<String> adicionarPacientes(@RequestBody AdicionaDTO adicionaDTO){
        profissionalSaudeService.adicionarPacientes(adicionaDTO);
        return ResponseEntity.status(200).body("Pacientes adicionados ao médico");
    }

    @GetMapping("/mostrar-pacientes")
    public ResponseEntity<List<Paciente>> mostrarPacientes(@RequestParam String nome){
        return ResponseEntity.ok(
                profissionalSaudeService.mostrarPacientes(nome)
        );
    }

}
