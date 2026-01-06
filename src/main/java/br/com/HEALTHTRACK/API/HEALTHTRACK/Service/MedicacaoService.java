package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;


import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.AtualizarMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.MedicacaoDuplicada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao.MedicacoesNaoLocalizadas;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Medicacao.MedicacaoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicacaoService {

    @Autowired
    private MedicacaoRepository medicacaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private MedicacaoMapper medicacaoMapper;


    public MedicacaoDetalheDTO cadastrarMedicacao(MedicacaoCadastroDTO dto){
        Medicacao medicacaoExiste = medicacaoRepository.findByNomeMedicamento(dto.nomeMedicamento());
        if(medicacaoExiste != null){
            throw new MedicacaoDuplicada("Medicacao ja cadastrada em sistema");
        }

        Optional<Medicacao> codigoMedicacao = medicacaoRepository.findByCodigoMedicamento(dto.codigoMedicamento());
        if(codigoMedicacao.isPresent()){
            throw new MedicacaoDuplicada("Codigo de medicacao ja cadastrado em sistema");
        }

        Medicacao medicacaoCadastrada = medicacaoMapper.toEntityCadastroMedicacao(dto);
        medicacaoRepository.save(medicacaoCadastrada);

        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacaoCadastrada);
    }

    public MedicacaoDetalheDTO buscarMedicacaoId(Long id){
        return medicacaoMapper.converteEntidadeParaDetalheDTO(
                medicacaoRepository.findById(id).orElseThrow(
                        () -> new MedicacoesNaoLocalizadas("Medicacao nao encontrada")
                )
        );
    }

    public List<MedicacaoDetalheDTO> listarMedicacoesAtivas(){
        List<Medicacao> medicacoes = medicacaoRepository.findAll()
                .stream()
                .filter(Medicacao::isAtivo)
                .toList();

        if(medicacoes.isEmpty()){
            throw new MedicacoesNaoLocalizadas("Nenhuma medicacao ativa no momento");
        }
        return medicacoes.stream()
                .map(medicacaoMapper::converteEntidadeParaDetalheDTO)
                .toList();
    }

    public List<MedicacaoDetalheDTO> listarTodasMedicacoes(){
        List<Medicacao> medicacoes = medicacaoRepository.findAll();

        if(medicacoes.isEmpty()){
            throw new MedicacoesNaoLocalizadas("Nenhuma medicacao cadastrada no momento");
        }
        return medicacoes.stream()
                .map(medicacaoMapper::converteEntidadeParaDetalheDTO)
                .toList();
    }

    public List<MedicacaoDetalheDTO> buscarMedicacaoPorNome(String termo){
        List<Medicacao> medicacoes = medicacaoRepository.findByTermoMedicacao(termo);

        if(medicacoes.isEmpty()){
            throw new MedicacoesNaoLocalizadas("Nenhuma medicacao foi localizada pelo termo: " + termo);
        }

        return medicacoes.stream()
                .map(medicacaoMapper::converteEntidadeParaDetalheDTO)
                .toList();
    }

    public MedicacaoDetalheDTO atualizarMedicacao(String nomeMedicacao, AtualizarMedicacaoDTO dto){

        Medicacao medicacaoExiste = medicacaoRepository.findByNomeMedicamento(nomeMedicacao);
        if(medicacaoExiste == null){
            throw new MedicacoesNaoLocalizadas("Medicacao Nao localizada com este nome");
        }
        Medicacao medicacaoDuplicada = medicacaoRepository.findByNomeMedicamento(dto.nomeMedicamento());
        if(medicacaoDuplicada != null){
            throw new MedicacaoDuplicada("Medicacao com este nome ja cadastrada em sistema");
        }
        Optional<Medicacao> codigoDuplicado = medicacaoRepository.findByCodigoMedicamento(dto.codigoMedicamento());
        if(codigoDuplicado.isPresent()){
            throw new MedicacaoDuplicada("Codigo de medicacao ja cadastrada em sistema");
        }

        if(dto.nomeMedicamento() != null && !dto.nomeMedicamento().equals(nomeMedicacao)){
            medicacaoExiste.setNomeMedicamento(dto.nomeMedicamento());
        }
        if(dto.codigoMedicamento() != null && !dto.codigoMedicamento().equals(medicacaoExiste.getCodigoMedicamento())){
            medicacaoExiste.setCodigoMedicamento(dto.codigoMedicamento());
        }
        if(dto.dosagemPadrao() != null && !dto.dosagemPadrao().equals(medicacaoExiste.getDosagemPadrao())){
            medicacaoExiste.setDosagemPadrao(dto.dosagemPadrao());
        }
        if(dto.forma() != null && !dto.forma().name().equals(medicacaoExiste.getForma().name())){
            medicacaoExiste.setForma(dto.forma());
        }
        if(dto.via() != null && !dto.via().name().equals(medicacaoExiste.getVia().name())){
            medicacaoExiste.setVia(dto.via());
        }

        medicacaoRepository.save(medicacaoExiste);
        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacaoExiste);
    }

    public MedicacaoDetalheDTO desativarMedicacao(String nomeMedicacao){
        Medicacao medicacao = medicacaoRepository.findByNomeMedicamento(nomeMedicacao);
        if(medicacao == null){
            throw new MedicacoesNaoLocalizadas("Medicacao com nome: " + nomeMedicacao + " nao foi localizada");
        }

        medicacao.setAtivo(false);
        medicacaoRepository.save(medicacao);
        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacao);
    }

    public MedicacaoDetalheDTO ativarMedicacao(String nomeMedicacao){
        Medicacao medicacao = medicacaoRepository.findByNomeMedicamento(nomeMedicacao);
        if(medicacao == null){
            throw new MedicacoesNaoLocalizadas("Medicacao com nome: " + nomeMedicacao + " nao foi localizada");
        }

        medicacao.setAtivo(true);
        medicacaoRepository.save(medicacao);
        return medicacaoMapper.converteEntidadeParaDetalheDTO(medicacao);
    }
}

