package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Alergia;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface AlergiaMapper {

    List<Alergia>toEntityList(List<AlergiaDTO> alergias);
    Alergia toEntity (AlergiaDTO dto);
    Alergia toEntityAlergiaCadastro(AlergiaCadastroDTO dto);
    AlergiaDetalheDTO toEntityAlergiaDetalheDTO(Alergia alergia);
}
