package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.DetalheSintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Sintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Doenca.DoencaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma.SintomaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
