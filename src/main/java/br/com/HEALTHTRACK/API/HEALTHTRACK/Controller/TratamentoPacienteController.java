package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.TratamentoPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Tratamento-paciente")
@Tag(name = "Tratamento do paciente", description = "Endpoints relacionados ao gerenciamento de tratamentos do paciente")
public class TratamentoPacienteController {

    @Autowired
    private TratamentoPacienteService tratamentoPacienteService;

    @Operation(
            summary = "Cadastrar novo tratamento do paciente",
            description = "Este endpoint é responsável por vincular o tratamento com o paciente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tratamento do paciente cadastrado com sucesso!",
                    content = @Content(
                            mediaType = "applcation/json",
                            schema = @Schema(implementation = TratamentoPacienteDetalhe.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Tratamento do paciente ja cadastrado em sistema",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TratamentoPacienteDTO.class)
                    )
            )
    })
    @PostMapping("/adiciona-paciente")
    public ResponseEntity<TratamentoPacienteDetalhe> vincula_paciente(TratamentoPacienteDTO tratamentoPacienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoPacienteService.registra(tratamentoPacienteDTO));
    }

}
