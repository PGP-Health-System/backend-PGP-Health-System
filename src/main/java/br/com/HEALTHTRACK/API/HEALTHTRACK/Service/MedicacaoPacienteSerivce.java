package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicacaoPacienteSerivce {

    @Autowired
    private MedicacaoPacienteRepository medicacaoPacienteRepository;

    public MedicacaoPacienteRepository getMedicacaoPacienteRepository() {
        return medicacaoPacienteRepository;
    }
}
