package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.AtualizarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.CadastrarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.DetalhesDoencaPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.DoencaPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doencas-pacientes")
@Tag(name = "Doencas do Paciente", description = "Endpoints relacionados ao gerenciamento de doenças dos pacientes")
public class DoencaPacienteController {

    private final DoencaPacienteService doencaPacienteService;

    public DoencaPacienteController(DoencaPacienteService doencaPacienteService) {
        this.doencaPacienteService = doencaPacienteService;
    }

    @Operation(
            summary = "Cadastrar Doença do Paciente",
            description = "Endpoint para cadastrar uma nova doença para um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doença do paciente cadastrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Paciente inativo nao pode ter doenca cadastrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<DetalhesDoencaPacienteDTO> cadastrarDoencaPaciente(@RequestBody @Valid CadastrarDoencaPaciente dto) {
        DetalhesDoencaPacienteDTO detalhesDoencaPacienteDTO = doencaPacienteService.cadastrarDoencaPaciente(dto);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }

    @Operation(
            summary = "Atualizar Doença do Paciente",
            description = "Endpoint para atualizar uma doença existente de um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doença do paciente atualizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença do paciente não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<DetalhesDoencaPacienteDTO> atualizarDoencaPaciente(
            @PathVariable String cpf,
            @RequestBody @Valid AtualizarDoencaPaciente dto) {
        DetalhesDoencaPacienteDTO detalhesDoencaPacienteDTO = doencaPacienteService.atualizarDoencaPaciente(cpf, dto);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }

    @Operation(
            summary = "Desativar doencao do Paciente",
            description = "Endpoint para desativar uma doença de um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doença do paciente desativada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença do paciente não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Doenca cronica nao pode ser desativada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/desativar/{cpf}")
    public ResponseEntity<DetalhesDoencaPacienteDTO> desativarDoencaPaciente(@PathVariable String cpf, @PathVariable String nomeDoenca) {
        DetalhesDoencaPacienteDTO detalhesDoencaPacienteDTO = doencaPacienteService.desativarDoenca(cpf, nomeDoenca);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }

    @Operation(
            summary = "Ativar doencao do Paciente",
            description = "Endpoint para ativar uma doença de um paciente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doença do paciente ativada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença do paciente não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/ativar/{cpf}")
    public ResponseEntity<DetalhesDoencaPacienteDTO> ativarDoencaPaciente(@PathVariable String cpf, @PathVariable String nomeDoenca) {
        DetalhesDoencaPacienteDTO detalhesDoencaPacienteDTO = doencaPacienteService.ativarDoenca(cpf, nomeDoenca);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }

    @Operation(
            summary = "Localizar doenca por Paciente",
            description = "Endpoint para localizar uma doença específica de um paciente pelo CPF e nome da doença."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doença do paciente localizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença do paciente não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/buscar/{cpf}/{nomeDoenca}")
    public ResponseEntity<DetalhesDoencaPacienteDTO> buscarDoencaPaciente(@PathVariable String cpf, @PathVariable String nomeDoenca) {
        DetalhesDoencaPacienteDTO detalhesDoencaPacienteDTO = doencaPacienteService.buscarDoencaPaciente(cpf, nomeDoenca);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }

    @Operation(
            summary = "Buscar todas as doenças por Paciente",
            description = "Endpoint para buscar todas as doenças associadas a um paciente pelo CPF."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doenças do paciente localizadas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesDoencaPacienteDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nenhuma doença encontrada para o paciente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/buscar/dados/{cpf}")
    public ResponseEntity<List<DetalhesDoencaPacienteDTO>> buscarDadosDoencaPaciente(@PathVariable String cpf) {
        List<DetalhesDoencaPacienteDTO> detalhesDoencaPacienteDTO = doencaPacienteService.mostrarTodasDoencasPaciente(cpf);
        return ResponseEntity.ok(detalhesDoencaPacienteDTO);
    }
}
