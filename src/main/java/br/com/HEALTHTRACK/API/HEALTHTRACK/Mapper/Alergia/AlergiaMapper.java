package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Alergia;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface AlergiaMapper {

    List<Alergia>toEntityList(List<AlergiaDTO> alergias);
    Alergia toEntity (AlergiaDTO dto);
}
