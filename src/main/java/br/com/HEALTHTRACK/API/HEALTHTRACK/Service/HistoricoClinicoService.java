package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.HistoricoClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoClinicoService {

    @Autowired
    private HistoricoClinicoRepository historicoClinicoRepository;

    public HistoricoClinicoRepository getHistoricoClinicoRepository() {
        return historicoClinicoRepository;
    }
}
