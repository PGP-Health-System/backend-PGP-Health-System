package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoPacienteService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/medicacao-paciente")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Medicação do Paciente", description = "Endpoints para gerenciamento de medicações vinculadas a pacientes")
public class MedicacaoPacienteController {

    @Autowired
    private MedicacaoPacienteService medicacaoPacienteService;

    @Autowired
    public MedicacaoPacienteService  medicacaoPacienteSerivce;

    @Operation(summary = "Registrar medicação para paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicação registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Paciente, profissional ou medicamento não encontrado")
    })
    @PostMapping
    public ResponseEntity<MedicacaoPacienteDetalhe> registrar(
            @RequestBody MedicacaoPacienteDTO dto) {

        MedicacaoPacienteDetalhe detalhe =
                this.medicacaoPacienteService.registraMedicacaoPaciente(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(detalhe);
    }

    @Operation(summary = "Buscar medicações por CPF do paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/paciente/{cpf}")
    public ResponseEntity<List<MedicacaoPacienteDetalhe>> buscarPorPaciente(
            @RequestParam String cpf) {

        List<MedicacaoPacienteDetalhe> lista =
                medicacaoPacienteService.buscarPorPaciente(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @Operation(summary = "Listar medicações ativas")
    @ApiResponse(responseCode = "200", description = "Lista de medicações ativas")
    @GetMapping("/ativas")
    public ResponseEntity<List<MedicacaoPacienteDetalhe>> buscarAtivos() {

        List<MedicacaoPacienteDetalhe> lista =
                medicacaoPacienteService.buscarAtivos();

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    @Operation(summary = "Desativar medicação do paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medicação desativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Medicação não encontrada")
    })
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        medicacaoPacienteService.desativarMedicacaoPaciente(id);
        return ResponseEntity.noContent().build();
    }
}

