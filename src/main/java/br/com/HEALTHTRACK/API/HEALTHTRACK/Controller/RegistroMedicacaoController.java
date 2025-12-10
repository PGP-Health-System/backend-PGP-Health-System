package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.RegistroMedicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro-medicacao")
public class RegistroMedicacaoController {

    @Autowired
    private RegistroMedicacaoService registroMedicacaoService;

    public RegistroMedicacaoService getRegistroMedicacaoService() {
        return registroMedicacaoService;
    }
}
