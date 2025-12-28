package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.DoencaPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.DoencaPaciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoencaPacienteMapper {

    DoencaPaciente converterDoencaPacienteEntidade(DoencaPaciente doencaPaciente);
    DoencaPaciente toDetalhesDTO(DoencaPaciente doencaPaciente);
}
