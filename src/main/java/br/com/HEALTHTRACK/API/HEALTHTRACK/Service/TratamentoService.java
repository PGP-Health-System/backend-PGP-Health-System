package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.CodigoCidNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.MedicacoesNaoLocalizadas;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento.TratamentoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TratamentoService {

    private final TratamentoRepository tratamentoRepository;

    private final DoencaRepository doencaRepository;

    private final MedicacaoPacienteRepository medicacaoPacienteRepository;

    private final TratamentoMapper tratamentoMapper;

    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public TratamentoService(TratamentoRepository tratamentoRepository, DoencaRepository doencaRepository, MedicacaoPacienteRepository medicacaoPacienteRepository,
                             TratamentoMapper tratamentoMapper, ProfissionalSaudeRepository profissionalSaudeRepository){
        this.tratamentoRepository = tratamentoRepository;
        this.doencaRepository = doencaRepository;
        this.medicacaoPacienteRepository = medicacaoPacienteRepository;
        this.tratamentoMapper = tratamentoMapper;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
    }



    public TratamentoDetalheDTO registra (TratamentoDTO tratamentoDTO){
        Doenca doenca = doencaRepository.findByCodigoCid(tratamentoDTO.codigoCid(), Doenca.class);
        if(doenca == null){
            throw new CodigoCidNaoLocalizado("Codigo cid não localizado Codigo: " + tratamentoDTO.codigoCid() + " tempo: " + LocalDateTime.now());
        }
        ProfissionalSaude profissionalSaude = profissionalSaudeRepository.findByEmail(tratamentoDTO.email()).orElseThrow(
                () -> new EmailNaoEncontrado("Não foi possivel localizar o email: " + tratamentoDTO.email()));

        List<MedicacaoPaciente> medicacaoList = medicacaoPacienteRepository.findAllByMedicacao_codigoMedicamento(tratamentoDTO.codigoMedicamento());
                if(medicacaoList.isEmpty()){
                    throw new MedicacoesNaoLocalizadas("Nenhuma medicacao foi localizada");
                }

        Tratamento tratamento = tratamentoMapper.converteParaEntidade(tratamentoDTO);
        tratamento.setDoenca(doenca);
        tratamento.setProfissionalSaude(profissionalSaude);
        tratamento.setMedicacoes(medicacaoList);

        tratamentoRepository.save(tratamento);

        return tratamentoMapper.converteEntidadeParaDetalhe(tratamento);

    }


}
