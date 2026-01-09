package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.TratamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento.TratamentoPacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.stereotype.Service;

@Service
public class TratamentoPacienteService {
    private TratamentoRepository tratamentoRepository;
    private PacienteRepository pacienteRepository;
    private DoencaRepository doencaRepository;
    private ProfissionalSaudeRepository profissionalSaudeRepository;
    private TratamentoPacienteRepository tratamentoPacienteRepository;
    private TratamentoPacienteMapper tratamentoPacienteMapper;

    public TratamentoPacienteService(TratamentoRepository tratamentoRepository, PacienteRepository pacienteRepository, DoencaRepository doencaRepository, ProfissionalSaudeRepository profissionalSaudeRepository, TratamentoPacienteRepository tratamentoPacienteRepository, TratamentoPacienteMapper tratamentoPacienteMapper) {
        this.tratamentoRepository = tratamentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.doencaRepository = doencaRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.tratamentoPacienteRepository = tratamentoPacienteRepository;
        this.tratamentoPacienteMapper = tratamentoPacienteMapper;
    }

    //Metodo diferente de Tratamento, este relaciona o paciente com o tratamento
    public TratamentoPacienteDetalhe registra(TratamentoPacienteDTO tratamentoPacienteDTO){

        Paciente pacientId = pacienteRepository.findByCpf(tratamentoPacienteDTO.cpf())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não encontrado com esse CPF"));

        Tratamento tratamentoId = tratamentoRepository.findByCodigoTratamento(tratamentoPacienteDTO.codigoTratamento())
                .orElseThrow(() -> new TratamentoNaoLocalizado("Tratamento não localizado códido errado"));

        Doenca doenca = doencaRepository.findByCodigoCid(
                        tratamentoPacienteDTO.codigoCid(), Doenca.class);

        ProfissionalSaude profissionalId = profissionalSaudeRepository.findByEmail(tratamentoPacienteDTO.email())
                .orElseThrow(() -> new EmailNaoEncontrado("Profissional não encontrado pelo Email"));

        TratamentoPaciente tratamentoPaciente = tratamentoPacienteMapper.ToEntidade(tratamentoPacienteDTO);
        tratamentoPaciente.setPaciente(pacientId);
        tratamentoPaciente.setDoenca(doenca);
        tratamentoPaciente.setProfissionalSaude(profissionalId);
        tratamentoPaciente.setTratamento(tratamentoId);

        TratamentoPaciente salvado = tratamentoPacienteRepository.save(tratamentoPaciente);

        return new TratamentoPacienteDetalhe(
                salvado.getTratamento().getId(),
                salvado.getPaciente().getId(),
                salvado.getProfissionalSaude().getId(),
                salvado.getDoenca().getId(),
                salvado.getDataInicio(),
                salvado.getDataFim(),
                salvado.getStatus()
        );


    }


}
