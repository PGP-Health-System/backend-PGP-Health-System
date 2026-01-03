package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.GravidadeAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.TipoAlergia;

public record AlergiaCadastroDTO(
        String nome,
        String descricao,
        TipoAlergia tipo,
        GravidadeAlergia gravidade,
        String cpfPaciente,
        String cpfResponsavelSaude
) {
}
