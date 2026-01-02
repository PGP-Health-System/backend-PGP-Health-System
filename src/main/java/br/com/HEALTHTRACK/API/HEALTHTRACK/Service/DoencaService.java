package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.AtualizarDoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.CodigoCidDuplicado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.CodigoCidNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.NomeDoencaDuplicada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.NomeDoencaNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Doenca.DoencaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoencaService {

    private final DoencaRepository doencaRepository;
    private final DoencaMapper doencaMapper;

    public DoencaService(DoencaRepository doencaRepository, DoencaMapper doencaMapper) {
        this.doencaRepository = doencaRepository;
        this.doencaMapper = doencaMapper;
    }

    public DoencaDetalhesDTO cadastrarDoenca(DoencaCadastroDTO doencaCadastroDTO){
        if(doencaRepository.findByCodigoCid(doencaCadastroDTO.codigoCid(), DoencaCadastroDTO.class) != null){
            throw new CodigoCidDuplicado("Doença com o mesmo código CID já cadastrada.");
        }
        if(doencaRepository.findByNomeDoenca(doencaCadastroDTO.nomeDoenca()) != null){
            throw new NomeDoencaDuplicada("Doença com o mesmo nome já cadastrada.");
        }
        doencaRepository.save(doencaMapper.converterDoencaEntidadeCadastro(doencaCadastroDTO));
        return doencaRepository.findByCodigoCid(doencaCadastroDTO.codigoCid(), DoencaDetalhesDTO.class);
    }

    public AtualizarDoencaDTO atualizarDoenca(String codigoCid,AtualizarDoencaDTO atualizarDoencaDTO){
        if(doencaRepository.findByCodigoCid(codigoCid, AtualizarDoencaDTO.class) == null){
            throw new CodigoCidNaoLocalizado("Doença com o código CID informado não encontrada.");
        }

        Doenca doenca = doencaRepository.findByCodigoCid(codigoCid, Doenca.class);

        if(atualizarDoencaDTO.nomeDoenca() != null){
            if(doencaRepository.findByNomeDoenca(atualizarDoencaDTO.nomeDoenca()) != null){
                throw new NomeDoencaDuplicada("Doença com o mesmo nome já cadastrada.");
            }
            doenca.setNomeDoenca(atualizarDoencaDTO.nomeDoenca());
        }

        if(atualizarDoencaDTO.descricao() != null){
            doenca.setDescricao(atualizarDoencaDTO.descricao());
        }

        doencaRepository.save(doenca);
        return atualizarDoencaDTO;
    }

    public DoencaDetalhesDTO detalheDoencas(String codigoCid){
        if(doencaRepository.findByCodigoCid(codigoCid, DoencaDetalhesDTO.class) == null){
            throw new CodigoCidNaoLocalizado("Doença com o código CID informado não encontrada.");
        }

        return doencaRepository.findByCodigoCid(codigoCid, DoencaDetalhesDTO.class);
    }

    public List<DoencaDetalhesDTO> listarTodasDoencas() {
        return doencaRepository.findAll()
                .stream()
                .map(doencaMapper::toDetalhesDTO)
                .toList();
    }

    public Doenca buscarPorNome(String nomeDoenca) {
        Doenca doenca = doencaRepository.findByNomeDoenca(nomeDoenca);
        if (doenca == null) {
            throw new NomeDoencaNaoLocalizado("Doença não encontrada pelo nome informado.");
        }
        return doenca;
    }
}
