package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Sintoma;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SintomaMapper {

    List<Sintoma>toEntityList(List<SintomaDTO> sintomas);
    Sintoma toEntity(SintomaDTO sintomaDTO);
}
