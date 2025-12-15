package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Usuario.UsuarioRegistroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario converterRegistroEntidade(UsuarioRegistroDTO usuarioRegistroDTO);
}
