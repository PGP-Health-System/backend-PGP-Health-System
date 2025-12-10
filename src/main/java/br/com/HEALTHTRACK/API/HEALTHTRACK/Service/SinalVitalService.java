package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.SinalVitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinalVitalService {

    @Autowired
    private SinalVitalRepository sinalVitalRepository;

    public SinalVitalRepository getSinalVitalRepository() {
        return sinalVitalRepository;
    }
}
