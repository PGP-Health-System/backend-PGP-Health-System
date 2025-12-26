package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    public ConsultaService getConsultaService() {
        return consultaService;
    }




}
