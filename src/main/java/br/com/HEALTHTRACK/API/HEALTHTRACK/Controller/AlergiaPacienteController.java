package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteAtualizarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AlergiaPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alegias-paciente")
@Tag(name = "AlergiaPaciente", description = "Endpoints relacionados a alergias de pacientes")
public class AlergiaPacienteController {

    private final AlergiaPacienteService alergiaPacienteService;

    public AlergiaPacienteController(AlergiaPacienteService alergiaPacienteService) {
        this.alergiaPacienteService = alergiaPacienteService;
    }

    @PostMapping("/cadastrar")
    @Operation(
        summary = "Cadastrar Alergia do Paciente",
        description = "Endpoint para cadastrar uma nova alergia para um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia do paciente cadastrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Alergia ja cadastrada para este paciente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou Alergia nao localizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public AlergiaPacienteDetalheDTO cadastrarAlergiaPaciente(@RequestBody @Valid AlergiaPacienteCadastroDTO dto){
        return alergiaPacienteService.cadastrarAlergiaPaciente(dto);
    }

    @GetMapping("/listar/alergias/{cpf}")
    @Operation(
        summary = "Listar Alergias do Paciente",
        description = "Endpoint para listar todas as alergias associadas a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de alergias do paciente retornada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente nao localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public List<AlergiaPacienteDetalheDTO> listarAlergiaPaciente(@PathVariable String cpf){
        return alergiaPacienteService.listarAlergiaPaciente(cpf);
    }

    @GetMapping("/buscar/{cpf}/{nomeAlergia}")
    @Operation(
        summary = "Buscar Alergia Especifica do Paciente",
        description = "Endpoint para buscar uma alergia especifica associada a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia do paciente retornada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou Alergia nao localizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public AlergiaPacienteDetalheDTO buscarAlergiaPacienteEspecifica(@PathVariable String cpf, @PathVariable String nomeAlergia){
        return alergiaPacienteService.buscarAlergiaPacienteEspecifica(cpf, nomeAlergia);
    }

    @PutMapping("/atualizar/{cpf}/{nomeAlergia}")
    @Operation(
        summary = "Atualizar Alergia do Paciente",
        description = "Endpoint para atualizar os detalhes de uma alergia associada a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia do paciente atualizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou Alergia nao localizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public AlergiaPacienteDetalheDTO atualizarAlergiaPaciente(@PathVariable String cpf, @PathVariable String nomeAlergia, @RequestBody @Valid AlergiaPacienteAtualizarDTO dto){
        return alergiaPacienteService.atualizarAlergiaPaciente(cpf, nomeAlergia, dto);
    }

    @PutMapping("/ativar/{cpf}/{nomeAlergia}")
    @Operation(
        summary = "Ativar Alergia do Paciente",
        description = "Endpoint para ativar uma alergia associada a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia do paciente ativada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou Alergia nao localizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public AlergiaPacienteDetalheDTO ativarAlergiaPaciente(@PathVariable String cpf, @PathVariable String nomeAlergia){
        return alergiaPacienteService.ativarAlergiaPacinte(cpf, nomeAlergia);
    }

    @PutMapping("/desativar/{cpf}/{nomeAlergia}")
    @Operation(
        summary = "Desativar Alergia do Paciente",
        description = "Endpoint para desativar uma alergia associada a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia do paciente desativada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente ou Alergia nao localizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public AlergiaPacienteDetalheDTO desativarAlergiaPaciente(@PathVariable String cpf, @PathVariable String nomeAlergia){
        return alergiaPacienteService.desativarAlergiaPaciente(cpf, nomeAlergia);
    }

    @GetMapping("/listar/ativas/{cpf}")
    @Operation(
        summary = "Listar Alergias Ativas do Paciente",
        description = "Endpoint para listar todas as alergias ativas associadas a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de alergias ativas do paciente retornada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente nao localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public List<AlergiaPacienteDetalheDTO> listarAlergiasAtivasPaciente(@PathVariable String cpf){
        return alergiaPacienteService.listarAlergiasAtivasPaciente(cpf);
    }

    @GetMapping("/listar/confirmadas/{cpf}")
    @Operation(
        summary = "Listar Alergias Confirmadas do Paciente",
        description = "Endpoint para listar todas as alergias confirmadas associadas a um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de alergias confirmadas do paciente retornada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente nao localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaPacienteDetalheDTO.class)
                    )
            )
    })
    public List<AlergiaPacienteDetalheDTO> listarAlergiasConfirmadas(@PathVariable String cpf){
        return alergiaPacienteService.listarAlergiasConfirmadas(cpf);
    }
}
