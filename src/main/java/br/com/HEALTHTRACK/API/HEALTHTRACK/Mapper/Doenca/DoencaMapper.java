package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Doenca;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoencaMapper {

    Doenca converterDoencaEntidadeCadastro(DoencaCadastroDTO doencaCadastroDTO);
    DoencaDTO doencaParaDoencaDTO(Doenca doenca);
    DoencaDetalhesDTO toDetalhesDTO(Doenca doenca);
}
