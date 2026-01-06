package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.AlergiaPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente.AlergiaPacienteDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlergiaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlergiaPacienteMapper {

    AlergiaPacienteDetalheDTO toEntityDetalheDTO(AlergiaPaciente alergiaPaciente);
    @Mapping(target = "id", ignore = true)
    AlergiaPaciente toEntityAlergiaPacienteCadastro(AlergiaPacienteCadastroDTO dto, Paciente pacienteLocalizado, Alergia alergiaLocalizada);
    AlergiaPaciente toEntityAlergiaPacienteAtualizar(AlergiaPaciente alergiaPacienteExistente);
}
