package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.PacienteService;
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
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/paciente")
@Tag(name = "Controle Paciente", description = "Controller responsavel por manipular e gerenciar o Paciente")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @Operation(
            summary = "Cadastrar Paciente",
            description = "Endpoint responsavel por realizar o cadastro dos pacientes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cadastro do paciente realizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email ou CPF do Paciente ja foram cadastrados em sistema!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data de Nascimento do Paciente nao pode ser futura!"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Falha ao realizar o cadastro do Paciente!"
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteDetalhesDTO> cadastrarPaciente(@RequestBody @Valid PacienteCadastroDTO pacienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrarPaciente(pacienteDTO));
    }

    @Operation(
            summary = "Atualizar Paciente",
            description = "Endpoint responsavel por atualizar os dados do pacientes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente atualizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteDetalhesDTO.class)
                    )
            )
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PacienteDetalhesDTO> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteAtualizacaoDTO dto) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, dto));
    }

    @Operation(
            summary = "Desativar Paciente",
            description = "Endpoint responsavel por realizar a desativacao dos pacientes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente desativado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente nao esta ativo!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao desativar o Paciente!"
            )
    })
    @PutMapping("/desativar/{id}")
    public ResponseEntity<PacienteDetalhesDTO> desativarPaciente(@PathVariable Long id){
        return ResponseEntity.status(200).body(pacienteService.desativarPaciente(id));
    }

    @Operation(
            summary = "Ativar Paciente",
            description = "Endpoint responsavel por realizar a ativacao dos pacientes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente ativado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente nao esta desativado!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao ativar o Paciente"
            )
    })
    @PutMapping("/ativar/{id}")
    public ResponseEntity<PacienteDetalhesDTO> ativarPaciente(@PathVariable Long id){
        return ResponseEntity.status(200).body(pacienteService.ativarPaciente(id));
    }

    @Operation(
            summary = "Buscar Paciente por ID",
            description = "Endpoint responsavel por realizar a busca do Paciente a partir do ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente localizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao localizar o paciente!"
            )
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDetalhesDTO> buscarPacienteId(@PathVariable Long id){
        return ResponseEntity.status(200).body(pacienteService.buscarDetalhes(id));
    }

    @Operation(
            summary = "Buscar todos os Pacientes",
            description = "Endpoint responsavel por realizar a busca de todos os Pacientes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pacientes localizados com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResumoDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao localizar os pacientes!"
            )
    })
    @GetMapping("/listar/todos")
    public ResponseEntity<List<PacienteResumoDTO>> listarTodosPacientes(){
        return ResponseEntity.status(200).body(pacienteService.listarTodos());
    }
}
