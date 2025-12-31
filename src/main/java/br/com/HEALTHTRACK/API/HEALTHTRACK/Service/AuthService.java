package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioLoginDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.UsuarioEnum.Role;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroDuploTipoUsuario;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao.ErroUsuarioExistente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.EmailDuplicado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Usuario.UsuarioMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.UsuarioRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private ProfissionalSaudeRepository profissionalSaudeRepository;
    private PacienteRepository pacienteRepository;
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
            UsuarioMapper usuarioMapper,
            PacienteRepository pacienteRepository,
            ProfissionalSaudeRepository profissionalSaudeRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
        this.pacienteRepository = pacienteRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
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

        Usuario usuario = usuarioMapper.converterRegistroEntidade(registroDTO);

        validarRegistro(registroDTO, usuario);

        usuario.setSenha(passwordEncoder.encode(registroDTO.senha()));
        usuario.setAtivo(true);
        usuario.setEmail(registroDTO.email());
        usuario.setRole(registroDTO.role());

        usuarioRepository.save(usuario);

        return "Usuário cadastrado com sucesso!";
    }




    private void validarRegistro(UsuarioRegistroDTO registroDTO, Usuario usuario) {

        if (usuarioRepository.localizarUsuario(registroDTO.username()) != null) {
            throw new ErroUsuarioExistente("Usuário já existente em sistema, tente novamente!");
        }

        if (registroDTO.profissionalSaude() != null &&
                registroDTO.paciente() != null) {
            throw new ErroDuploTipoUsuario(
                    "Um usuário não pode ser tanto profissional de saúde quanto paciente ao mesmo tempo!"
            );
        }


        if (registroDTO.role() == Role.PACIENTE) {
            Paciente paciente = pacienteRepository.findByEmail(registroDTO.email());
            if (!pacienteRepository.existsByEmail(registroDTO.email())) {
                throw new PacienteNaoLocalizado(
                        "Paciente não encontrado. Cadastre o paciente antes."
                );
            }
            usuario.setPaciente(paciente);

        }


        if (registroDTO.role() == Role.MEDICO || registroDTO.role() == Role.ENFERMEIRO) {
            ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findByEmail(registroDTO.email())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
            if (!profissionalSaudeRepository.existsByEmail(registroDTO.email())) {
                throw new EmailNaoEncontrado(
                        "Profissional não encontrado. Cadastre o profissional antes."
                );
            }

            usuario.setProfissionalSaude(profissionalSaude);

        }

        if (registroDTO.role().equals(Role.ADMIN)) {
            usuario.setPaciente(null);
            usuario.setProfissionalSaude(null);
        }
    }
}
