package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.AtualizarDoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.DoencaSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doencas")
@Tag(name = "Controle Doenca", description = "Controller responsavel por manipular e gerenciar as Doencas")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class DoencaController {

    private final DoencaSerivce doencaSerivce;

    public DoencaController(DoencaSerivce doencaSerivce){
        this.doencaSerivce = doencaSerivce;
    }

    @Operation(
            summary = "Cadastrar Doenca",
            description = "Endpoint responsavel por realizar o cadastro das doencas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cadastro da doenca realizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoencaDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Doença com o mesmo código CID já cadastrada ou Doença com o mesmo nome já cadastrada."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Falha ao realizar o cadastro da Doenca!"
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<DoencaDetalhesDTO> cadastrarDoenca(@RequestBody @Valid DoencaCadastroDTO doencaCadastroDTO){
        doencaSerivce.cadastrarDoenca(doencaCadastroDTO);
        return ResponseEntity.status(201).body(doencaSerivce.detalheDoencas(doencaCadastroDTO.codigoCid()));
    }

    @Operation(
            summary = "Atualizar Doenca",
            description = "Endpoint responsavel por realizar a atualizacao das doencas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doenca atualizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoencaDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença com o código CID informado não encontrada."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Doença com o mesmo nome já cadastrada."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Falha ao realizar a atualizacao da Doenca!"
            )
    })
    @PutMapping("/atualizar/{codigoCid}")
    public ResponseEntity<DoencaDetalhesDTO> atualizarDoenca(@PathVariable String codigoCid, @RequestBody @Valid AtualizarDoencaDTO atualizarDoencaDTO){
        doencaSerivce.atualizarDoenca(codigoCid, atualizarDoencaDTO);
        return ResponseEntity.status(200).body(doencaSerivce.detalheDoencas(codigoCid));
    }

    @Operation(
            summary = "Buscar Doenca",
            description = "Endpoint responsavel por realizar a busca das doencas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doenca localizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoencaDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doença com o código CID informado não encontrada."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Falha ao realizar a atualizacao da Doenca!"
            )
    })
    @GetMapping("/detalhes/{codigoCid}")
    public ResponseEntity<DoencaDetalhesDTO> detalheDoenca(@PathVariable String codigoCid){
        return ResponseEntity.status(200).body(doencaSerivce.detalheDoencas(codigoCid));
    }

    @Operation(
            summary = "Buscar Doenca",
            description = "Endpoint responsavel por realizar a busca das doencas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doenca localizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoencaDetalhesDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Falha ao realizar a atualizacao da Doenca!"
            )
    })
    @GetMapping("/listar/todas")
    public ResponseEntity<Iterable<DoencaDetalhesDTO>> listarTodasDoencas(){
        return ResponseEntity.status(200).body(doencaSerivce.listarTodasDoencas());
    }
}
