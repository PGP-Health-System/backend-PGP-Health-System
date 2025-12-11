package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.PacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.UsuarioMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.UsuarioRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/entrar")
public class AuthenticationController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    @Autowired
    private UsuarioMapper usuarioMapper;

    public AuthenticationController(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.gerarToken((Usuario) Objects.requireNonNull(auth.getPrincipal()));

            return ResponseEntity.status(200).body("Usuário logado com sucesso: " + token);
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
            Usuario novoUsuario = usuarioMapper.converterRegistroEntidade(registroDTO);
            usuarioRepository.save(novoUsuario);
        } else if (registroDTO.paciente() != null) {
            Usuario novoUsuario = usuarioMapper.converterRegistroEntidade(registroDTO);
            usuarioRepository.save(novoUsuario);
        } else {
            Usuario novoUsuario = usuarioMapper.converterRegistroEntidade(registroDTO);
            usuarioRepository.save(novoUsuario);
        }
        return ResponseEntity.status(201).body("Usuário registrado com sucesso!");
    }
}
