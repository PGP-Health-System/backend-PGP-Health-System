package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoPacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicacao-paciente")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class MedicacaoPacienteController {

    @Autowired
    private MedicacaoPacienteService medicacaoPacienteService;

    @Autowired
    public MedicacaoPacienteService  medicacaoPacienteSerivce;


    @PostMapping
    public ResponseEntity<MedicacaoPacienteDetalhe> registrar(
            @RequestBody MedicacaoPacienteDTO dto) {

        MedicacaoPacienteService medicacaoPacienteService;
        MedicacaoPacienteDetalhe detalhe =
                this.medicacaoPacienteService.registraMedicacaoPaciente(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(detalhe);
    }


    @GetMapping("/paciente/{cpf}")
    public ResponseEntity<List<MedicacaoPacienteDetalhe>> buscarPorPaciente(
            @RequestParam String cpf) {

        List<MedicacaoPacienteDetalhe> lista =
                medicacaoPacienteService.buscarPorPaciente(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }


    @GetMapping("/ativas")
    public ResponseEntity<List<MedicacaoPacienteDetalhe>> buscarAtivos() {

        List<MedicacaoPacienteDetalhe> lista =
                medicacaoPacienteService.buscarAtivos();

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        medicacaoPacienteService.desativarMedicacaoPaciente(id);
        return ResponseEntity.noContent().build();
    }
}

