package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.RegistroMedicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.RegistroMedicacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface RegistroMedicacaoMapper {


    RegistroMedicacao toEntity(RegistroMedicacaoDTO registroMedicacaoDTO);

    RegistroMedicacaoDetalhe toDetail(RegistroMedicacao registroMedicacao);

}
