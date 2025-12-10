package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicacao")
public class MedicacaoController {

    @Autowired
    private MedicacaoService medicacaoService;

    public MedicacaoService getMedicacaoService() {
        return medicacaoService;
    }
}
