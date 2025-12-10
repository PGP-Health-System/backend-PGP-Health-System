package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicacaoService {

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    public MedicacaoRepository getMedicacaoRepository() {
        return medicacaoRepository;
    }
}
