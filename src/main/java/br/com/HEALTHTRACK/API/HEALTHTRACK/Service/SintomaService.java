package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Sintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.GravidadeSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.TipoSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaInvalidoException;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Doenca.DoencaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma.SintomaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SintomaService {

    @Autowired
    private SintomaRepository sintomaRepository;
    @Autowired
    private SintomaMapper sintomaMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    @Autowired
    private DoencaRepository doencaRepository;

    @Autowired
    private DoencaMapper doencaMapper;

    public SintomaRepository getSintomaRepository() {
        return sintomaRepository;
    }


    public DetalheSintomaDTO registarSintoma(SintomaDTO sintomaDTO) {
        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findByEmail(sintomaDTO.emailProfissional())
                .orElseThrow(() -> new EmailNaoEncontrado("Profisisonal não encontrado com esse email."));

        Paciente paciente = pacienteRepository.findByCpf(sintomaDTO.pacienteCpf())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não localizado por esse cpf"));

        Doenca doenca = doencaRepository.findByCodigoCid(sintomaDTO.codigoCid(), Doenca.class);
        DoencaDTO dto = doencaMapper.doencaParaDoencaDTO(doenca);

        Sintoma sintoma = sintomaMapper.toEntity(sintomaDTO);
        sintoma.setDoenca(doenca);
        sintoma.setPaciente(paciente);
        sintoma.setProfissionalSaude(profissionalSaude);

        sintomaRepository.save(sintoma);
        DetalheSintomaDTO detalheSintomaDTO = sintomaMapper.toDetalhe(sintoma);

        return detalheSintomaDTO;
    }


    public DetalheSintomaDTO atualizaSintoma(SintomaDTO sintomaDTO) {
        Sintoma sintoma = sintomaRepository.findByNome(sintomaDTO.nome())
                .orElseThrow(() -> new SintomaInvalidoException("Sintoma não encontrado por esse nome"));

        ProfissionalSaude profissionalSaude = profissionalSaudeRepository
                .findByEmail(sintomaDTO.emailProfissional())
                .orElseThrow(() -> new EmailNaoEncontrado("Profissional não encontrado"));

        Paciente paciente = pacienteRepository
                .findByCpf(sintomaDTO.pacienteCpf())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não localizado"));

        Doenca doenca = doencaRepository
                .findByCodigoCid(sintomaDTO.codigoCid(), Doenca.class);

        sintomaMapper.updateEntityFromDto(sintomaDTO, sintoma);

        sintoma.setProfissionalSaude(profissionalSaude);
        sintoma.setPaciente(paciente);
        sintoma.setDoenca(doenca);

        sintomaRepository.save(sintoma);

        return sintomaMapper.toDetalhe(sintoma);
    }

    public List<Sintoma> listarSintomasPorNome(String nome) {
        if (nome == null) {
            return sintomaRepository.findAll();
        }
        return sintomaRepository.findByNomeIgnoreCase(nome);
    }

    public Sintoma buscarSintomaPorId(Long id){
       return sintomaRepository.findById(id)
                .orElseThrow(() -> new SintomaInvalidoException("Sintoma não encontrado por esse nome"));
    }


    public List<TipoSintoma> tiposSintomas(){
        return List.of(TipoSintoma.values());
    }

    public Sintoma aumentarGravidade(Long id){
       Sintoma sintoma = buscarSintomaPorId(id);
       sintoma.setGravidade(sintoma.getGravidade().aumentar());
       return sintomaRepository.save(sintoma);
    }

    public Sintoma diminuirGravidade(Long id){
        Sintoma sintoma = buscarSintomaPorId(id);
        sintoma.setGravidade(sintoma.getGravidade().diminuir());
        return sintomaRepository.save(sintoma);
    }

}
