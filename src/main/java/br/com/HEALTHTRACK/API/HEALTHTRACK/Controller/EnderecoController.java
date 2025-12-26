package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco.EnderecoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco.EnderecoResponseDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.EnderecoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    @PostMapping("/enderecos")
    public ResponseEntity<Map<String, EnderecoDTO>> registraEndereco(@RequestBody EnderecoResponseDTO enderecoResponseDTO) throws IOException, InterruptedException {
        EnderecoDTO enderecoDTO = enderecoService.salvaEndereco(enderecoResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Sucesso!", enderecoDTO));
    }
}
