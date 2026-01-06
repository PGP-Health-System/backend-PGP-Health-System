package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.AtualizarMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Map;

@RestController
@RequestMapping("/medicacao")
@Tag(name = "Controle medicação", description = "Controller para manipular e gerenciar entidade de medicação")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class MedicacaoController {

  private final MedicacaoService medicacaoService;

  public MedicacaoController(MedicacaoService medicacaoService){
      this.medicacaoService = medicacaoService;
  }

  @PostMapping("/cadastrar")
  @Operation(
          summary = "Cadastrar medicacao",
          description = "Responsavel por cadastrar uma nova medicacao no sistema"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Medicacao cadastrada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "409",
                  description = "Nome de medicacao, ou Codigo ja cadastrado em sistema",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public MedicacaoDetalheDTO cadastrarMedicacao(@RequestBody MedicacaoCadastroDTO dto){
      return medicacaoService.cadastrarMedicacao(dto);
  }

  @GetMapping("/listar/ativas")
  @Operation(
          summary = "Listar ativas",
          description = "Responsavel por realizar a listagem de medicacoes ativas"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Listagem realizada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Nenhuma medicacao ativa no momento",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public List<MedicacaoDetalheDTO> listarMedicacoesAtivas(){
      return medicacaoService.listarMedicacoesAtivas();
  }

  @GetMapping("listar/todas")
  @Operation(
          summary = "Listar todas",
          description = "Responsavel por listar todas as medicacoes"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Listagem realizada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Nenhuma medicacao cadastrada em sistema.",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public List<MedicacaoDetalheDTO> listarTodasMedicacoes(){
      return medicacaoService.listarTodasMedicacoes();
  }

  @GetMapping("/buscar/{termo}")
  @Operation(
          summary = "Buscar medicacao",
          description = "Responsavel por localizar a medicacao a partir de uma palavra"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Medicacao localizada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Nenhuma medicacao Localizada com este termo",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public List<MedicacaoDetalheDTO> buscarMedicacaoPorNome(@PathVariable String termo){
      return medicacaoService.buscarMedicacaoPorNome(termo);
  }

  @PutMapping("/atualizar")
  @Operation(
          summary = "Atualizar medicamento",
          description = "Responsavel por realizar a atualizacao da medicacao"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Medicacao atualizada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Medicacao nao localizada",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "409",
                  description = "Nome ou Codigo de medicacao em duplicidade",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public MedicacaoDetalheDTO atualizarMedicacao(@PathVariable String nomeMedicacao, AtualizarMedicacaoDTO dto){
      return medicacaoService.atualizarMedicacao(nomeMedicacao, dto);
  }

  @PutMapping("/desativar/{nomeMedicacao}")
  @Operation(
          summary = "Desativar medicacao",
          description = "Responsavel por desativar a medicacao"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Medicacao desativada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Medicacao nao localizada",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public MedicacaoDetalheDTO desativarMedicacao(@PathVariable String nomeMedicacao){
      return medicacaoService.desativarMedicacao(nomeMedicacao);
  }

  @PutMapping("/ativar/{nomeMedicacao}")
  @Operation(
          summary = "Ativar medicacao",
          description = "Responsavel por ativar a medicacao"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Medicacao ativada com sucesso!",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Medicacao nao localizada",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = MedicacaoDetalheDTO.class)
                  )
          )
  })
  public MedicacaoDetalheDTO ativarMedicacao(@PathVariable String nomeMedicacao){
      return medicacaoService.ativarMedicacao(nomeMedicacao);
  }
}
