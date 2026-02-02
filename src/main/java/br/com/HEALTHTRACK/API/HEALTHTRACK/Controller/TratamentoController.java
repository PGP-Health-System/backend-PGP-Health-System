package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.ErroCadastrarTratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.TratamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.Map;

@RestController
@RequestMapping("/tratamento")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Tratamentos", description = "Endpoints para gerenciamento de tratamentos médicos")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;


    public TratamentoService getTratamentoService() {
        return tratamentoService;
    }

    @Operation(
            summary = "Registrar tratamento",
            description = "Registra um novo tratamento para o paciente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tratamento registrado com sucesso",
                    content = @Content(schema = @Schema(implementation = TratamentoDetalheDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao cadastrar tratamento",
                    content = @Content
            )
    })
    @PostMapping("/registra")
    public ResponseEntity<Map<String, TratamentoDetalheDTO>> registraTratamento(TratamentoDTO tratamentoDTO) {
        return ResponseEntity.status(200).body(Map.of("Sucesso!", tratamentoService.registra(tratamentoDTO)));
    }

}
