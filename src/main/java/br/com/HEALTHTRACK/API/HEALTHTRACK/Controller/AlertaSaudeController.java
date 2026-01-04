package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlertaSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AlertaSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas-saude")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Alerta de Saúde", description = "Endpoints relacionados aos alertas de saúde dos pacientes")
public class AlertaSaudeController {

    private final AlertaSaudeService alertaSaudeService;

    public AlertaSaudeController (AlertaSaudeService alertaSaudeService) {
        this.alertaSaudeService = alertaSaudeService;
    }

    @Operation(
            summary = "Cadastrar novo alerta",
            description = "Endpoint responsavel por cadastrar um novo alerta de saúde para um paciente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta cadastrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente nao localizado para cadastro de alerta",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeCadastroDTO.class)
                    )
            )
    })
    @PostMapping("/cadastrar")
    public AlertaSaudeDetalheDTO cadastrarAlerta(@RequestBody @Valid AlertaSaudeCadastroDTO dto){
        return alertaSaudeService.cadastrarAlerta(dto);
    }

    @Operation(
            summary = "Buscar alerta por ID",
            description = "Endpoint responsavel por buscar um alerta de saúde pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta encontrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta nao localizado com o ID informado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Long.class)
                    )
            )
    })
    @GetMapping("/bsucar/{id}")
    public AlertaSaudeDetalheDTO buscarAlertaPorId(@PathVariable Long id){
        return alertaSaudeService.buscarAlertaPorId(id);
    }

    @Operation(
            summary = "Listar alertas por paciente",
            description = "Endpoint responsavel por listar todos os alertas de saúde de um paciente pelo seu CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alertas listados com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nenhum alerta de saúde encontrado para este Paciente ou Paciente nao localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/listar/paciente/{cpf}")
    public List<AlertaSaude> listarPorPaciente(@PathVariable String cpf){
        return alertaSaudeService.buscarAlertaPorCpf(cpf);
    }

    @Operation(
            summary = "Listar alertas ativos",
            description = "Endpoint responsavel por listar todos os alertas de saúde que ainda não foram resolvidos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alertas ativos listados com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeDetalheDTO.class)
                    )
            )
    })
    @GetMapping("/listar/ativos")
    public List<AlertaSaudeDetalheDTO> listarAtivos(){
        return alertaSaudeService.listarAtivos();
    }

    @Operation(
            summary = "Resolver alerta de saúde",
            description = "Endpoint responsavel por marcar um alerta de saúde como resolvido"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta marcado como resolvido com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlertaSaudeDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta nao localizado com o ID informado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Long.class)
                    )
            )
    })
    @PutMapping("/resolver/{id}")
    public AlertaSaudeDetalheDTO resolverAlerta(@PathVariable Long id){
        return alertaSaudeService.alterarResolvido(id);
    }
}
