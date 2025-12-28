package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.CadastrarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.DetalhesDoencaPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.DoencaPaciente.DoencaPacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class DoencaPacienteService {

    private final DoencaRepository doencaRepository;
    private final DoencaPacienteMapper doencaPacienteMapper;
    private final PacienteRepository pacienteRepository;

    public DoencaPacienteService(DoencaRepository doencaRepository, DoencaPacienteMapper doencaPacienteMapper, PacienteRepository pacienteRepository) {
        this.doencaRepository = doencaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doencaPacienteMapper = doencaPacienteMapper;
    }

    public DetalhesDoencaPacienteDTO cadastrarDoencaPaciente(CadastrarDoencaPaciente dto) {


    }
}
