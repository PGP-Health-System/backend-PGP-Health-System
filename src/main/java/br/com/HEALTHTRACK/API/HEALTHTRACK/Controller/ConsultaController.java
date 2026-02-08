package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    public ConsultaService getConsultaService() {
        return consultaService;
    }


    @Operation(
            summary = "Criar uma nova consulta",
            description = "Cria uma consulta vinculando paciente, profissional de saúde e registros de medicação"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Consulta criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou registros de medicação não encontrados",
                    content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ConsultaDetalhe.class)
    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou profissional de saúde não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ConsultaDetalhe.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ConsultaDetalhe.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Void> criarConsulta(
            @RequestBody ConsultaDTO consultaDTO) {
        consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
