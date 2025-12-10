package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlergiaService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    public AlergiaRepository getAlergiaRepository() {
        return alergiaRepository;
    }
}
