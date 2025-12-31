package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
