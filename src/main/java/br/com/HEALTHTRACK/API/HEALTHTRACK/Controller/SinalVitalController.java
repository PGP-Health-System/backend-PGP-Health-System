package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.SinalVital;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.SinalVitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sinal-vital")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Sinais Vitais", description = "Endpoints para gerenciamento de sinais vitais dos pacientes")
public class SinalVitalController {

    private final SinalVitalService sinalVitalService;

    public SinalVitalController(SinalVitalService sinalVitalService){
        this.sinalVitalService = sinalVitalService;
    }

    @Operation(
            summary = "Cadastrar Sinal Vital",
            description = "Registra um novo sinal vital para um paciente específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sinal vital registrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos para o registro do sinal vital ou paciente inativo.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            )
    })
    public SinalVitalDetalhesDTO registrarSinalVital(@RequestBody @Valid SinalVitalCadastroDTO dto){
        return sinalVitalService.registrarSinalVital(dto);
    }

    @Operation(
            summary = "Buscar Sinal Vital por ID",
            description = "Recupera os detalhes de um sinal vital específico usando seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sinal vital encontrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sinal vital não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            )
    })
    public SinalVitalDetalhesDTO buscarSinalVitalPorId(@PathVariable Long id){
        return sinalVitalService.buscarPorId(id);
    }

    @Operation(
            summary = "Listar Sinais Vitais por Paciente",
            description = "Recupera todos os sinais vitais registrados para um paciente específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sinais vitais recuperados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVital.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVital.class)
                    )
            )
    })
    public List<SinalVital> listarSinalVitalPorPaciente(@PathVariable String cpf){
        return sinalVitalService.listarSinalVitalPorPaciente(cpf);
    }

    @Operation(
            summary = "Listar Sinais Vitais por Período",
            description = "Recupera todos os sinais vitais registrados para um paciente específico dentro de um intervalo de datas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sinais vitais recuperados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVital.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVital.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Período inválido fornecido.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVital.class)
                    )
            )
    })
    public List<SinalVital> listarSinalVitalPorPeriodo(@PathVariable String cpf, @RequestBody LocalDate dataInicio, LocalDate dataFim){
        return sinalVitalService.listarSinalVitalPorPeriodo(dataInicio, dataFim, cpf);
    }

    @Operation(
            summary = "Buscar Último Registro de Sinal Vital",
            description = "Recupera o último sinal vital registrado para um paciente específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Último sinal vital recuperado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou sinal vital não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SinalVitalDetalhesDTO.class)
                    )
            )
    })
    public SinalVitalDetalhesDTO buscarUltimoRegistro(@PathVariable String cpf){
        return sinalVitalService.buscarUltimoRegistro(cpf);
    }
}
