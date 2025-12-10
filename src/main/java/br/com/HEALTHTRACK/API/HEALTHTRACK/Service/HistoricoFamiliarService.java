package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.HistoricoFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoFamiliarService {

    @Autowired
    private HistoricoFamiliarRepository historicoFamiliarRepository;

    public HistoricoFamiliarRepository getHistoricoFamiliarRepository() {
        return historicoFamiliarRepository;
    }
}
