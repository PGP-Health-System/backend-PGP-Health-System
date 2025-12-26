package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrar")
@Tag(name = "Autenticação", description = "Endpoints responsáveis pela autenticação e registro de usuários")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
    this.authService = authService;
    }

    // COMENTARIO PEDRO BORBA -- 15/12/2025
    // Ajustei algumas coisas aqui dentro, coloquei as regras dentro de AuthService para dar uma limpada
    // Também ajustei alguns retornos, testei no Insomnia e esta tudo indo :)


    @Operation(
            summary = "Realizar Login Usuario",
            description = "Controller responsavel por realizar o login dos usuarios"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario logado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Dados de Usuario invalido!"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {
        return ResponseEntity.status(200).body(authService.login(loginDTO));
    }

    @Operation(
            summary = "Realiza o Cadastro do Usuario",
            description = "Controller responsavel por realizar o cadastro dos usuarios"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario cadastrado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Usuario já cadastrado em sistema, tente novamente!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um usuário não pode ser tanto profissional de saúde quanto paciente ao mesmo tempo!"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Erro ao realizar o cadastro do usuario"
            )

    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody @Valid UsuarioRegistroDTO registroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registro(registroDTO));
    }
}
