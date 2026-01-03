package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Paciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PacienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profissionalSaude", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "statusPaciente", ignore = true)
    @Mapping(target = "doencas", ignore = true)
    Paciente convertePacienteEntidadeCadastro(PacienteCadastroDTO pacienteCadastroDTO);


    @Mapping(target = "historicoFamiliar", ignore = true)
    @Mapping(target = "doencas", ignore = true)
    PacienteDetalhesDTO converterPacienteDtoDetalhes(Paciente paciente);


    @Mapping(target = "historicoFamiliar", ignore = true)
    @Mapping(target = "doencas", ignore = true)
    PacienteResumoDTO converterPacienteResumoDto(Paciente paciente);
}
