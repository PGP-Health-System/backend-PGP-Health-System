package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Sintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.TipoSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaInvalidoException;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma.SintomaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.SintomaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sintoma")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;
    @Autowired
    private SintomaMapper sintomaMapper;

    public SintomaService getSintomaService() {
        return sintomaService;
    }


    @PostMapping("/registra")
    public ResponseEntity<DetalheSintomaDTO> registraSintoma(@RequestBody SintomaDTO sintomaDTO) {
        try {
            return ResponseEntity.status(200).body(sintomaService.registarSintoma(sintomaDTO));
        } catch (SintomaInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/atualiza/sintoma")
    public ResponseEntity<DetalheSintomaDTO> atualizarSintoma(@RequestBody SintomaDTO sintomaDTO) {
        try {
            return ResponseEntity.status(201).body(sintomaService.atualizaSintoma(sintomaDTO));
        } catch (SintomaInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar-sintomas")
    public ResponseEntity<List<DetalheSintomaDTO>> buscarSintomas(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok().body(
                sintomaService.listarSintomasPorNome(nome)
                        .stream()
                        .map(sintomaMapper::toDetalhe)
                        .toList());
    }

    @GetMapping("/tipos-sintomas")
    public ResponseEntity<List<TipoSintoma>> buscarTiposDeSintomas() {
        return ResponseEntity.ok().body(
                sintomaService.tiposSintomas());
    }

    @PutMapping("/{id}/aumentar-gravidade")
    public ResponseEntity<Void> aumentarGravidade(@PathVariable Long id) {
        sintomaService.aumentarGravidade(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/diminuir-gravidade")
    public ResponseEntity<Void> diminuirGravidade(@PathVariable Long id) {
        sintomaService.diminuirGravidade(id);
        return ResponseEntity.noContent().build();
    }

}
