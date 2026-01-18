package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaInvalidoException;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.SintomaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sintoma")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;

    public SintomaService getSintomaService() {
        return sintomaService;
    }

    @PostMapping("/registra")
    public ResponseEntity<DetalheSintomaDTO> registraSintoma(@RequestBody SintomaDTO sintomaDTO){
        try {
            return ResponseEntity.status(200).body(sintomaService.registarSintoma(sintomaDTO));
        }catch (SintomaInvalidoException e){
            return ResponseEntity.badRequest().build();
        }


    }
}
