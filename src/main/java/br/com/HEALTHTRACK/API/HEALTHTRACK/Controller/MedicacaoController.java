package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/medicacao")
@Tag(name = "Controle medicação", description = "Controller para manipular e gerenciar entidade de medicação")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class MedicacaoController {

    @Autowired
    private MedicacaoService medicacaoService;


    @Operation(
            summary = "Registrar medicamento.",
            description = "Registra medicamento do paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Medicamento registrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao registrar medicamento"
            )
    })
    @PostMapping("/registra")
    public ResponseEntity<Map<String, MedicacaoDetalheDTO>> registraMedicacao
            (@RequestBody MedicacaoDTO medicacaoDTO) {
        MedicacaoDetalheDTO medicacao = medicacaoService.registraMedicamento(medicacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Sucesso!", medicacao));
    }
}
