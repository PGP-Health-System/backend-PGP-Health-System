package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.MedicacaoPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicacaoPacienteMapper {

    MedicacaoPaciente toEntity(MedicacaoPacienteDTO medicacaoPacienteDTO);

    MedicacaoPacienteDetalhe toDetalhe(MedicacaoPaciente medicacaoPaciente);

}
