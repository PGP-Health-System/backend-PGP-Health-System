package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Usuario.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Usuario.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.UsuarioRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrar")
public class AuthenticationController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
            var auth = authenticationManager.authenticate(usernamePassword);

            return ResponseEntity.status(200).body("Usuário logado com sucesso: " + auth.getPrincipal());
        } catch (Exception e){
            throw new UsernameNotFoundException("Usuário ou senha inválidos!");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid UsuarioRegistroDTO registroDTO) {
        if (usuarioRepository.localizarUsuario(registroDTO.email()) != null) {
            throw new IllegalArgumentException("Usuário já existe!");
        }

        String senhaCriptografada = passwordEncoder.encode(registroDTO.senha());
        if (registroDTO.profissionalSaude() != null && registroDTO.paciente() != null) {
            throw new IllegalArgumentException("Um usuário não pode ser tanto profissional de saúde quanto paciente ao mesmo tempo!");
        }

        if (registroDTO.profissionalSaude() != null) {
            Usuario novoUsuario = Usuario.builder()
                    .username(registroDTO.email())
                    .senha(senhaCriptografada)
                    .role(registroDTO.role())
                    .profissionalSaude(registroDTO.profissionalSaude())
                    .ativo(true)
                    .build();
            usuarioRepository.save(novoUsuario);
        } else if (registroDTO.paciente() != null) {
            Usuario novoUsuario = Usuario.builder()
                    .username(registroDTO.email())
                    .senha(senhaCriptografada)
                    .role(registroDTO.role())
                    .paciente(registroDTO.paciente())
                    .ativo(true)
                    .build();
            usuarioRepository.save(novoUsuario);
        } else {
            throw new IllegalArgumentException("Um usuário deve ser associado a um profissional de saúde ou a um paciente!");
        }
        return ResponseEntity.status(201).body("Usuário registrado com sucesso!");
    }
}
