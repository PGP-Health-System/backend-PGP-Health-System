package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.MedicacaoPacienteSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicacao-paciente")
public class MedicacaoPacienteController {

    @Autowired
    private MedicacaoPacienteSerivce medicacaoPacienteSerivce;

    public MedicacaoPacienteSerivce getMedicacaoPacienteSerivce() {
        return medicacaoPacienteSerivce;
    }
}
