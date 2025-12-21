package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Consulta;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConsultaMapper {
}
