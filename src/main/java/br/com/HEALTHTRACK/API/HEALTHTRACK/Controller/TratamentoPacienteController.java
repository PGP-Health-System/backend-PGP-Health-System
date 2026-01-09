package br.com.HEALTHTRACK.API.HEALTHTRACK.Controller;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Service.TratamentoPacienteService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Tratamento-paciente")
public class TratamentoPacienteController {

    @Autowired
    private TratamentoPacienteService tratamentoPacienteService;

    @PostMapping("/adiciona-paciente")
    public ResponseEntity<TratamentoPacienteDetalhe> vincula_paciente(TratamentoPacienteDTO tratamentoPacienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoPacienteService.registra(tratamentoPacienteDTO));
    }

}
