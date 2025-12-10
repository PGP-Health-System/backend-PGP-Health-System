package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.AlergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaService alergiaService;

    public AlergiaService getAlergiaService() {
        return alergiaService;
    }
}
