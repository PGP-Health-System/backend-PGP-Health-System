package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Profissional;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional.ProfissionalDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ProfissionalMapper {

    ProfissionalSaude ProfissionalSaudeParaEntidade(ProfissionalDTO profissionalDTO);

    ProfissionalDetalhe EntidadeParaDetalhe(ProfissionalSaude profissionalSaude);

}
