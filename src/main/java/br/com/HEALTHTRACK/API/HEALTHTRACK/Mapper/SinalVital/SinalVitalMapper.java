package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.SinalVital;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.SinalVital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SinalVitalMapper {

    SinalVital toEntity(SinalVitalCadastroDTO dto);
    SinalVitalDetalhesDTO toDetalhesDTO(SinalVital entity);
}
