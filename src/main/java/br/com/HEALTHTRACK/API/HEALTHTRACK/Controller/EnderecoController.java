package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.EnderecoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.EnderecoResponseDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    @PostMapping("/enderecos")
    public ResponseEntity<Map<String, EnderecoDTO>> registraEndereco(@RequestBody EnderecoResponseDTO enderecoResponseDTO){
        EnderecoDTO enderecoDTO = enderecoService.salvaEndereco(enderecoResponseDTO);
        return ResponseEntity.status(201).body(Map.of("Sucesso!", enderecoDTO));
    }
}
