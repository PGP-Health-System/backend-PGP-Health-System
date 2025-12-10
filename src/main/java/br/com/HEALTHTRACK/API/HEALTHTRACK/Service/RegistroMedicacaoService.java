package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.RegistroMedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroMedicacaoService {

    @Autowired
    private RegistroMedicacaoRepository registroMedicacaoRepository;

    public RegistroMedicacaoRepository getRegistroMedicacaoRepository() {
        return registroMedicacaoRepository;
    }
}
