package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;

public record AtualizarMedicacaoDTO(
        String nomeMedicamento,
        String codigoMedicamento,
        String dosagemPadrao,
        FormaFarmaceutica forma,
        ViaAdministracao via
) {
}
