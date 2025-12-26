package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.ErroCadastrarTratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Security.SecurityConfiguration;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.TratamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.Map;

@RestController
@RequestMapping("/tratamento")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;


    public TratamentoService getTratamentoService() {
        return tratamentoService;
    }


    @PostMapping("/registra")
    public ResponseEntity<Map<String,String>> registraTratamento(TratamentoDTO tratamentoDTO){
        try{
            TratamentoDetalheDTO tratamentoDetalheDTO = tratamentoService.registra(tratamentoDTO);
            return ResponseEntity.status(200).body(Map.of("Sucesso!", "Tratamento cadastrado"));
        }catch (ErroCadastrarTratamento e){
            return ResponseEntity.status(500).body(Map.of("Error!", "Não foi possivel cadastrar o tratamento"));
        }
    }

}
