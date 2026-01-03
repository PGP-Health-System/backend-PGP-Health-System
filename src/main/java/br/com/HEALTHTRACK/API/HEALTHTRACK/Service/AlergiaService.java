package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaAtualizacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.GravidadeAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.TipoAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Alergia.AlergiaDuplicada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Alergia.AlergiaNaoLocalizada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Alergia.AlergiaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlergiaService {

    private final AlergiaRepository alergiaRepository;
    private final AlergiaMapper alergiaMapper;

    public AlergiaService(AlergiaRepository alergiaRepository, AlergiaMapper alergiaMapper) {
        this.alergiaRepository = alergiaRepository;
        this.alergiaMapper = alergiaMapper;
    }

    public AlergiaDetalheDTO cadastrarAlergia(AlergiaCadastroDTO alergiaCadastroDTO){

        if(alergiaRepository.findByNome(alergiaCadastroDTO.nome()).isPresent()){
            throw new AlergiaDuplicada("Alergia ja cadastrada");
        }

        Alergia alergiaConvertida = alergiaMapper.toEntityAlergiaCadastro(alergiaCadastroDTO);
        alergiaRepository.save(alergiaConvertida);

        return alergiaMapper.toEntityAlergiaDetalheDTO(alergiaConvertida);
    }

    public AlergiaDetalheDTO atualizarAlergia(String nomeAlergia, AlergiaAtualizacaoDTO alergiaAtualizacaoDTO){

        Alergia alergiaExistente = alergiaRepository.findByNome(nomeAlergia)
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada"));

        if(alergiaAtualizacaoDTO.nome() != null && !alergiaAtualizacaoDTO.nome().equals(nomeAlergia)){
            alergiaExistente.setNome(alergiaAtualizacaoDTO.nome());
        }

        if(alergiaAtualizacaoDTO.descricao() != null){
            alergiaExistente.setDescricao(alergiaAtualizacaoDTO.descricao());
        }

        if(alergiaAtualizacaoDTO .tipo() != null){
            alergiaExistente.setTipo(alergiaAtualizacaoDTO.tipo());
        }

        if(alergiaAtualizacaoDTO.gravidade() != null){
            alergiaExistente.setGravidade(alergiaAtualizacaoDTO.gravidade());
        }

        alergiaRepository.save(alergiaExistente);

        return alergiaMapper.toEntityAlergiaDetalheDTO(alergiaExistente);
    }

    public AlergiaDetalheDTO obterDetalhesAlergia(String nomeAlergia){

        Alergia alergiaExistente = alergiaRepository.findByNome(nomeAlergia)
                .orElseThrow(() -> new AlergiaNaoLocalizada("Alergia nao encontrada"));

        return alergiaMapper.toEntityAlergiaDetalheDTO(alergiaExistente);
    }

    public List<TipoAlergia> listarTiposAlergias(){
        return List.of(TipoAlergia.values());
    }

    public List<GravidadeAlergia> listarGravidadesAlergias(){
        return List.of(GravidadeAlergia.values());
    }

    public List<Alergia> listarAlergias(){
        return alergiaRepository.findAll();
    }

    public List<Alergia> listarAlergiasAtivas(){
        return alergiaRepository.findByAtivoTrue();
    }

    public List<Alergia> listarAlergiasInativas(){
        return alergiaRepository.findAll().stream()
                .filter(alergia -> !alergia.isAtivo())
                .toList();
    }
}
