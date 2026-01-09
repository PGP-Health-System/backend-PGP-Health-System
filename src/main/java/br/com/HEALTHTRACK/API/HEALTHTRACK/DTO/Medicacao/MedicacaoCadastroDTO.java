package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados da medicação")
public record MedicacaoCadastroDTO(
        String nomeMedicamento,
        String codigoMedicamento,
        String dosagemPadrao,
        FormaFarmaceutica forma,
        ViaAdministracao via
) {
}
