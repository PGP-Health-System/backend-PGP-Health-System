package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface TratamentoMapper {

    Tratamento converteParaEntidade(TratamentoDTO tratamentoDTO);

    TratamentoDetalheDTO converteEntidadeParaDetalhe(Tratamento tratamento);

}
