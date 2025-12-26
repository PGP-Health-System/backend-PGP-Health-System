package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.ErroAtivarMedicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.ErroDesativarMedicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.TratamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Medicacao.MedicacaoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.TratamentoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Getter
public class MedicacaoService {

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private MedicacaoMapper medicacaoMapper;


    public MedicacaoDetalheDTO registraMedicamento(MedicacaoDTO medicacaoDTO) {
        Paciente paciente = pacienteRepository.findByCpf(medicacaoDTO.cpfPaciente())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não foi localizado pelo CPF"));

        Tratamento tratamento = tratamentoRepository.findByNome(medicacaoDTO.nomeTratamento())
                .orElseThrow(() -> new TratamentoNaoLocalizado("TratamentoDTO não localizado pelo nome"));

        Medicacao novaMedicacao =
                medicacaoMapper.converteParaEntidade(medicacaoDTO, paciente, tratamento);
        novaMedicacao.setDataInicio(LocalDate.now());
        novaMedicacao.setAtivo(true);

        Medicacao medicacao = medicacaoRepository.save(novaMedicacao);

        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacao);
    }

    public MedicacaoDetalheDTO desativarMedicamento(MedicacaoDTO medicacaoDTO) {
        Medicacao medicacao = medicacaoRepository.findByCodigoMedicamento(medicacaoDTO.codigoMedicamento())
                .orElseThrow(() -> new ErroDesativarMedicacao("Erro: medicação não encontrada pelo nome ou código"));

        if (!medicacao.isAtivo()){
            throw new ErroAtivarMedicacao("Medicação está desativada");
        }
        medicacao.setAtivo(false);
        medicacaoRepository.save(medicacao);
        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacao);
    }

    public MedicacaoDetalheDTO ativarMedicamento(MedicacaoDTO medicacaoDTO) {
        Medicacao medicacao = medicacaoRepository.findByCodigoMedicamento(medicacaoDTO.codigoMedicamento())
                .orElseThrow(() -> new ErroAtivarMedicacao("Erro: medicação não encontrada pelo nome ou código"));

        if (medicacao.isAtivo()){
            throw new ErroAtivarMedicacao("Medicação já está ativa");
        }
        medicacao.setAtivo(true);
        medicacaoRepository.save(medicacao);
        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacao);
    }
}

