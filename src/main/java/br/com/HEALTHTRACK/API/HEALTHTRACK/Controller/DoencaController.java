package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.DoencaSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doencas")
public class DoencaController {

    @Autowired
    private DoencaSerivce doencaSerivce;

    public DoencaSerivce getDoencaSerivce() {
        return doencaSerivce;
    }
}
