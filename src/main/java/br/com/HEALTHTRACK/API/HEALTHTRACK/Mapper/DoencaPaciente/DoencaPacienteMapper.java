package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.DoencaPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.AtualizarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.CadastrarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.DetalhesDoencaPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.DoencaPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoencaPacienteMapper {

    DetalhesDoencaPacienteDTO toDetalhesDTO(DoencaPaciente doencaPaciente);
    @Mapping(target = "historicoFamiliar", ignore = true)
    DoencaPaciente converterDtoEntidadeCadastro(CadastrarDoencaPaciente cadastrarDoencaPaciente);
    List<DetalhesDoencaPacienteDTO> toDetalhesListDTO(List<DoencaPaciente> doencasPaciente);
}
