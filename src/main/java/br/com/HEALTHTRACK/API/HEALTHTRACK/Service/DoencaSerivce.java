package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoencaSerivce {

    @Autowired
    private DoencaRepository doencaRepository;

    public DoencaRepository getDoencaRepository() {
        return doencaRepository;
    }
}
