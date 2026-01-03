package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.HistoricoFamiliar;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar.HistoricoFamiliarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.HistoricoFamiliar;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoFamiliarMapper {

    @Mapping(target = "historicoFamiliar", ignore = true)
    @NotNull
    List<HistoricoFamiliar> toEntityList(List<HistoricoFamiliarDTO> historicosFamiliares);
}
