package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroLogin;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroRegistro;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrar")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
    this.authService = authService;
    }

    // COMENTARIO PEDRO BORBA -- 15/12/2025
    // Ajustei algumas coisas aqui dentro, coloquei as regras dentro de AuthService para dar uma limpada
    // Também ajustei alguns retornos, testei no Insomnia e esta tudo indo :)

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {
        try{
            return ResponseEntity.status(200).body(authService.login(loginDTO));
        } catch (ErroLogin e){
            throw new ErroLogin("Usuário ou senha inválidos!");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody @Valid UsuarioRegistroDTO registroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.registro(registroDTO));
        } catch (ErroRegistro e) {
            throw new ErroRegistro("Falha ao realizar o registro: " + e.getMessage());
        }
    }
}
