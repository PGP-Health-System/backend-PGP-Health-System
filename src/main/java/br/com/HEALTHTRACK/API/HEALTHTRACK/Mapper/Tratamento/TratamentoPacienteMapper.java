package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.TratamentoPaciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface TratamentoPacienteMapper {


    TratamentoPaciente ToEntidade(TratamentoPacienteDTO tratamentoPacienteDTO);

    TratamentoPacienteDetalhe ToDetalhe (TratamentoPacienteDTO tratamentoPacienteDTO);


}
