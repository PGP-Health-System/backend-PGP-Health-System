package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.RegistroMedicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro-medicacao")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Registro de Medicação", description = "Endpoints para registro de administração de medicamentos")
public class RegistroMedicacaoController {

    private final RegistroMedicacaoService registroMedicacaoService;

    public RegistroMedicacaoController(RegistroMedicacaoService registroMedicacaoService) {
        this.registroMedicacaoService = registroMedicacaoService;
    }

    @Operation(
            summary = "Registrar administração de medicamento",
            description = "Registra a administração de um medicamento a um paciente por um profissional de saúde"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Registro de medicação realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegistroMedicacaoDetalhe.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Paciente, medicação ou profissional não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<RegistroMedicacaoDetalhe> registrarMedicacao(
            @RequestBody @Valid RegistroMedicacaoDTO registroMedicacaoDTO
    ) {
        RegistroMedicacaoDetalhe detalhe =
                registroMedicacaoService.registrarMedicacao(registroMedicacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhe);
    }

}
