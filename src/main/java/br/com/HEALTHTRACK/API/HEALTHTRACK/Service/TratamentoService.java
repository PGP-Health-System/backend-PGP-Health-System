package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;


import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.MedicacoesNaoLocalizadas;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento.TratamentoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private DoencaRepository doencaRepository;

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    @Autowired
    TratamentoMapper tratamentoMapper;

    @Autowired
    ProfissionalSaudeRepository profissionalSaudeRepository;


    @Transactional
    public TratamentoDetalheDTO registra(TratamentoDTO tratamentoDTO) {
        List<Medicacao> medicacaoList = medicacaoRepository.findAllByCodigoMedicamento(tratamentoDTO.codigoMedicamento());

        if (medicacaoList.isEmpty()) {
            throw new MedicacoesNaoLocalizadas(
                    "Medicações não localizadas pelo codigo do medicamento"
            );
        }

        String ultimoCodigo = tratamentoRepository.findCodigoMax();
        long novoCodigo = (ultimoCodigo != null) ? Long.parseLong(ultimoCodigo) + 1 : 1;

        Tratamento tratamento = tratamentoMapper.converteParaEntidade(tratamentoDTO);
        tratamento.setMedicacoes(medicacaoList);
        tratamento.setCodigoTratamento(String.format("%08d", novoCodigo));

        tratamentoRepository.save(tratamento);

        return tratamentoMapper.converteEntidadeParaDetalhe(tratamento);

    }


}
