package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.AtualizarTratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.DoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface TratamentoMapper {

    Tratamento converteParaEntidade(TratamentoDTO tratamentoDTO);

    TratamentoDetalheDTO converteEntidadeParaDetalhe(Tratamento tratamento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "medicacoes", ignore = true)
    Tratamento toEntity(TratamentoDTO tratamentoDTO);

    Tratamento paraEntity(Tratamento tratamento);


    void atualizaEntity(AtualizarTratamentoDTO atualizarTratamentoDTO, @MappingTarget Tratamento tratamento);

}
