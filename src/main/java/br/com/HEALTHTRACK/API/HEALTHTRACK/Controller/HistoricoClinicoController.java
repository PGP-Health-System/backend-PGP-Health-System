package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.HistoricoClinico;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.HistoricoClinicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico-clinico")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class HistoricoClinicoController {

    @Autowired
    private HistoricoClinicoService historicoClinicoService;

    public HistoricoClinicoService getHistoricoClinicoService() {
        return historicoClinicoService;
    }
}
