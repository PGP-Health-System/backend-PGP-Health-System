package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.AlertaSaude;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude.AlertaSaudeResumoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlertaSaude;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertaSaudeMapper {

    AlertaSaude toEntity(AlertaSaudeCadastroDTO dto);
    AlertaSaudeResumoDTO toResumoDTO(AlertaSaude alerta);
    AlertaSaudeDetalheDTO toDetalheDTO(AlertaSaude alerta);
}
