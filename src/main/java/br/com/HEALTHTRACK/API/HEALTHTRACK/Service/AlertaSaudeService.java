package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlertaSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertaSaudeService {

    @Autowired
    private AlertaSaudeRepository alertaSaudeRepository;

    public AlertaSaudeRepository getAlertaSaudeRepository() {
        return alertaSaudeRepository;
    }
}
