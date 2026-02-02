package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;


import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.MedicacoesNaoLocalizadas;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.TratamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento.TratamentoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TratamentoService {

    private StringBuilder stringBuilder;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    private final DoencaRepository doencaRepository;

    private final MedicacaoPacienteRepository medicacaoPacienteRepository;

    private final TratamentoMapper tratamentoMapper;

    private final ProfissionalSaudeRepository profissionalSaudeRepository;


    public TratamentoService(TratamentoRepository tratamentoRepository, DoencaRepository doencaRepository, MedicacaoPacienteRepository medicacaoPacienteRepository, TratamentoMapper tratamentoMapper, ProfissionalSaudeRepository profissionalSaudeRepository) {
        this.tratamentoRepository = tratamentoRepository;
        this.doencaRepository = doencaRepository;
        this.medicacaoPacienteRepository = medicacaoPacienteRepository;
        this.tratamentoMapper = tratamentoMapper;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
    }

    @Transactional
    public TratamentoDetalheDTO registra(TratamentoDTO tratamentoDTO) {
        List<MedicacaoPaciente> medicacaoList = medicacaoPacienteRepository.findAllByMedicacao_codigoMedicamento(tratamentoDTO.codigoMedicamento());

        verificaListaMedicacao(medicacaoList);

        String ultimoCodigo = tratamentoRepository.findCodigoMax();
        long novoCodigo = (ultimoCodigo != null) ? Long.parseLong(ultimoCodigo) + 1 : 1;

        Tratamento tratamento = tratamentoMapper.converteParaEntidade(tratamentoDTO);
        String codigoTratamento = String.format("%08d", novoCodigo);

        tratamento.setCodigoTratamento("TRT-" + codigoTratamento);
        tratamentoRepository.save(tratamento);

        return tratamentoMapper.converteEntidadeParaDetalhe(tratamento);

    }




    public boolean verificaListaMedicacao(List<MedicacaoPaciente> list) {
        if (list == null || list.isEmpty()) {
            throw new MedicacoesNaoLocalizadas(
                    "Medicações não localizadas pelo código do medicamento"
            );
        }
        return true;
    }

    public Tratamento buscarTratanemntoExistente(String codigoTratamento) {
        Tratamento tratamentoId = tratamentoRepository.findByCodigoTratamento(codigoTratamento)
                .orElseThrow(() -> new TratamentoNaoLocalizado("Tratamento não localizado códido errado"));
        return tratamentoId;
    }

}
