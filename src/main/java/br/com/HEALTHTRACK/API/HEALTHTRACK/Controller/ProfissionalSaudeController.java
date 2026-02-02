package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.AdicionaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional-saude")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Profissional de Saúde", description = "Endpoints para gerenciamento de profissionais de saúde")
public class ProfissionalSaudeController {

    @Autowired
    private ProfissionalSaudeService profissionalSaudeService;

    public ProfissionalSaudeService getProfissionalSaudeService() {
        return profissionalSaudeService;
    }

    @Operation(
            summary = "Cadastrar profissional de saúde",
            description = "Realiza o cadastro de um novo profissional de saúde no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profissional cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProfissionalDetalhe.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<ProfissionalDetalhe> cadastrar(@RequestBody ProfissionalDTO profissionalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSaudeService.cadastrar(profissionalDTO));
    }


    @Operation(
            summary = "Adicionar pacientes ao profissional",
            description = "Vincula um ou mais pacientes a um profissional de saúde"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pacientes adicionados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional ou paciente não encontrado", content = @Content)
    })
    @RequestMapping(value = "/adicionar-pacientes", method = RequestMethod.PATCH)
    public ResponseEntity<String> adicionarPacientes(@RequestBody AdicionaDTO adicionaDTO) {
        profissionalSaudeService.adicionarPacientes(adicionaDTO);
        return ResponseEntity.status(200).body("Pacientes adicionados ao médico");
    }


    @Operation(
            summary = "Listar pacientes do profissional",
            description = "Retorna a lista de pacientes vinculados a um profissional pelo nome"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado", content = @Content)
    })
    @GetMapping("/mostrar-pacientes")
    public ResponseEntity<List<Paciente>> mostrarPacientes(@RequestParam String nome) {
        return ResponseEntity.ok(
                profissionalSaudeService.mostrarPacientes(nome)
        );
    }

    @Operation(
            summary = "Remover paciente do profissional",
            description = "Remove o vínculo de um paciente com o profissional de saúde"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Paciente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente ou profissional não encontrado", content = @Content)
    })
    @DeleteMapping("/remover-pacientes")
    public ResponseEntity<String> removerPacientes(@RequestParam @Parameter(description = "Nome do profissional", example = "Dr. Pedro")
                                                   String nome, @RequestParam @Parameter(description = "Email do paciente", example = "pedro@email.com")
                                                   String email) {


        profissionalSaudeService.deletarPaciente(nome, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Paciente removido com sucesso.");
    }

}
