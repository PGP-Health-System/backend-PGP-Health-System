package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.Frequencia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;

import java.time.LocalDate;

public record MedicacaoDetalheDTO(
        String nomeMedicamento,
        String codigoMedicamento,
        String dosagem,
        FormaFarmaceutica forma,
        ViaAdministracao via,
        Frequencia frequencia,
        String observacoes,
        boolean ativo
) {
}
