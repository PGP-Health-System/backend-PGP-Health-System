package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Consulta;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta.ConsultaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapper {

    Consulta toEntity(ConsultaDTO consultaDTO);

    ConsultaDetalhe toDetail(Consulta consulta);


}
