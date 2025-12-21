package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Usuario;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Usuario converterRegistroEntidade(UsuarioRegistroDTO usuarioRegistroDTO);
}
