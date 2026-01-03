package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.GravidadeAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.TipoAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AlergiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alergias")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Alergias", description = "Endpoints relacionados ao gerenciamento de alergias")
public class AlergiaController {

    private final AlergiaService alergiaService;

    public AlergiaController(AlergiaService alergiaService) {
        this.alergiaService = alergiaService;
    }

    @Operation(
            summary = "Cadastrar novas alergias",
            description = "Responsavel por realizar o cadastro de novas alergias no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia cadastrada com sucesso!",
                    content = @Content(
                            mediaType = "applcation/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Alergia ja cadastrada em sistema",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =  AlergiaCadastroDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos para o cadastro de alergia",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaCadastroDTO.class)
                    )
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<AlergiaDetalheDTO> cadastrarAlergia(@RequestBody @Valid AlergiaCadastroDTO dto)
    {
        AlergiaDetalheDTO detalheDTO = alergiaService.cadastrarAlergia(dto);
        return ResponseEntity.ok(detalheDTO);
    }

    @Operation(
            summary = "Atualizar alergias",
            description = "Responsavel por realizar a atualizacao de alergias no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergia atualizada com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alergia nao encontrada no sistema",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos para a atualizacao de alergia",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            )
    })
    @PutMapping("/atualizar/{nomeAlergia}")
    public ResponseEntity<AlergiaDetalheDTO> atualizarAlergia(@PathVariable String nomeAlergia, @RequestBody @Valid AlergiaAtualizacaoDTO dto)
    {
        AlergiaDetalheDTO detalheDTO = alergiaService.atualizarAlergia(nomeAlergia, dto);
        return ResponseEntity.ok(detalheDTO);
    }

    @Operation(
            summary = "Obter detalhes de uma alergia",
            description = "Responsavel por obter os detalhes de uma alergia especifica no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalhes da alergia obtidos com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alergia nao encontrada no sistema",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlergiaDetalheDTO.class)
                    )
            )
    })
    @GetMapping("/detalhes/{nomeAlergia}")
    public ResponseEntity<AlergiaDetalheDTO> obterAlergiaDetalhada(@PathVariable @Valid String nomeAlergia){
        AlergiaDetalheDTO detalheDTO = alergiaService.obterDetalhesAlergia(nomeAlergia);
        return ResponseEntity.ok(detalheDTO);
    }

    @Operation(
            summary = "Obter tipos de alergias",
            description = "Responsavel por obter os tipos de alergias disponiveis no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipos de alergias obtidos com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlergia.class)
                    )
            )
    })
    @GetMapping("/tipos")
    public ResponseEntity<List<TipoAlergia>> obterTiposAlergia(){
        List<TipoAlergia> alergias = alergiaService.listarTiposAlergias();
        return ResponseEntity.ok(alergias);
    }

    @Operation(
            summary = "Obter gravidades de alergias",
            description = "Responsavel por obter as gravidades de alergias disponiveis no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Gravidades de alergias obtidos com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GravidadeAlergia.class)
                    )
            )
    })
    @GetMapping("/gravidades")
    public ResponseEntity<List<GravidadeAlergia>> obterGravidadesAlergia(){
        List<GravidadeAlergia> gravidades = alergiaService.listarGravidadesAlergias();
        return ResponseEntity.ok(gravidades);
    }

    @Operation(
            summary = "Listar todas as alergias",
            description = "Responsavel por listar todas as alergias cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergias listadas com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Alergia.class)
                    )
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Alergia>> listarAlergias(){
        List<Alergia> alergias = alergiaService.listarAlergias();
        return ResponseEntity.ok(alergias);
    }

    @Operation(
            summary = "Listar alergias ativas",
            description = "Responsavel por listar todas as alergias ativas cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergias ativas listadas com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Alergia.class)
                    )
            )
    })
    @GetMapping("/listar/ativas")
    public ResponseEntity<List<Alergia>> listarAlergiasAtivas(){
        List<Alergia> alergias = alergiaService.listarAlergiasAtivas();
        return ResponseEntity.ok(alergias);
    }

    @Operation(
            summary = "Listar alergias inativas",
            description = "Responsavel por listar todas as alergias inativas cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alergias inativas listadas com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Alergia.class)
                    )
            )
    })
    @GetMapping("/listar/inativas")
    public ResponseEntity<List<Alergia>> listarAlergiasInativas(){
        List<Alergia> alergias = alergiaService.listarAlergiasInativas();
        return ResponseEntity.ok(alergias);
    }
}
