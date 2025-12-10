package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AlertaSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alertas-saude")
public class AlertaSaudeController {

    @Autowired
    private AlertaSaudeService alertaSaudeService;

    public AlertaSaudeService getAlertaSaudeService() {
        return alertaSaudeService;
    }
}
