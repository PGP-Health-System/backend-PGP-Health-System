package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.TratamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tratamento")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;

    public TratamentoService getTratamentoService() {
        return tratamentoService;
    }
}
