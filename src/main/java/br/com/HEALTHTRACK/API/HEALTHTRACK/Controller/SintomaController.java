package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Sintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.TipoSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaInvalidoException;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma.SintomaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.SintomaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Sintomas", description = "Endpoints para gerenciamento de sintomas")
public class SintomaController {

    @Autowired
    private SintomaService sintomaService;
    @Autowired
    private SintomaMapper sintomaMapper;

    public SintomaService getSintomaService() {
        return sintomaService;
    }


    @Operation(
            summary = "Registrar sintoma",
            description = "Registra um novo sintoma para o paciente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sintoma registrado com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalheSintomaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Sintoma inválido", content = @Content)
    })
    @PostMapping("/registra")
    public ResponseEntity<DetalheSintomaDTO> registraSintoma(@RequestBody SintomaDTO sintomaDTO) {
        try {
            return ResponseEntity.status(200).body(sintomaService.registarSintoma(sintomaDTO));
        } catch (SintomaInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Atualizar sintoma",
            description = "Atualiza as informações de um sintoma existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sintoma atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalheSintomaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Sintoma inválido", content = @Content)
    })
    @PatchMapping("/atualiza/sintoma")
    public ResponseEntity<DetalheSintomaDTO> atualizarSintoma(@RequestBody SintomaDTO sintomaDTO) {
        try {
            return ResponseEntity.status(201).body(sintomaService.atualizaSintoma(sintomaDTO));
        } catch (SintomaInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Listar sintomas",
            description = "Lista todos os sintomas ou filtra por nome"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de sintomas retornada com sucesso")
    })
    @GetMapping("/listar-sintomas")
    public ResponseEntity<List<DetalheSintomaDTO>> buscarSintomas(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok().body(
                sintomaService.listarSintomasPorNome(nome)
                        .stream()
                        .map(sintomaMapper::toDetalhe)
                        .toList());
    }


    @Operation(
            summary = "Listar tipos de sintomas",
            description = "Retorna todos os tipos de sintomas disponíveis no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipos de sintomas retornados com sucesso")
    })
    @GetMapping("/tipos-sintomas")
    public ResponseEntity<List<TipoSintoma>> buscarTiposDeSintomas() {
        return ResponseEntity.ok().body(
                sintomaService.tiposSintomas());
    }

    @Operation(
            summary = "Aumentar gravidade do sintoma",
            description = "Incrementa o nível de gravidade de um sintoma pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Gravidade aumentada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sintoma não encontrado", content = @Content)
    })
    @PutMapping("/{id}/aumentar-gravidade")
    public ResponseEntity<Void> aumentarGravidade(@PathVariable Long id) {
        sintomaService.aumentarGravidade(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Diminuir gravidade do sintoma",
            description = "Decrementa o nível de gravidade de um sintoma pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Gravidade diminuída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sintoma não encontrado", content = @Content)
    })
    @PutMapping("/{id}/diminuir-gravidade")
    public ResponseEntity<Void> diminuirGravidade(@PathVariable Long id) {
        sintomaService.diminuirGravidade(id);
        return ResponseEntity.noContent().build();
    }

}
