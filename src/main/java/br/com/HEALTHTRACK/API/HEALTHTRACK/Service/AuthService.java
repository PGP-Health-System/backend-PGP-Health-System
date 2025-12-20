package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.UsuarioMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.UsuarioRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UsuarioMapper usuarioMapper;

    public AuthService(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            UsuarioMapper usuarioMapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
    }

    public String login(UsuarioLoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(),
                loginDTO.senha()
        );

        var authentication = authenticationManager.authenticate(authToken);

        Usuario usuario = (Usuario) Objects.requireNonNull(authentication.getPrincipal());

        return tokenService.gerarToken(usuario);
    }

    public String registro(UsuarioRegistroDTO registroDTO) {

        validarRegistro(registroDTO);

        Usuario usuario = usuarioMapper.converterRegistroEntidade(registroDTO);
        usuario.setSenha(passwordEncoder.encode(registroDTO.senha()));
        usuario.setAtivo(true);
        usuario.setRole(registroDTO.role());

        if (registroDTO.paciente() != null) {
            usuario.setPaciente(registroDTO.paciente());
        }

        if (registroDTO.profissionalSaude() != null) {
            usuario.setProfissionalSaude(registroDTO.profissionalSaude());
        }

        usuarioRepository.save(usuario);

        return "Usuário cadastrado com sucesso!";
    }

    private void validarRegistro(UsuarioRegistroDTO registroDTO) {

        if (usuarioRepository.localizarUsuario(registroDTO.username()) != null) {
            throw new IllegalArgumentException("Usuário já existe!");
        }

        if (registroDTO.profissionalSaude() != null &&
                registroDTO.paciente() != null) {
            throw new IllegalArgumentException(
                    "Um usuário não pode ser tanto profissional de saúde quanto paciente ao mesmo tempo!"
            );
        }
    }
}
