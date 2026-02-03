package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.RegistroMedicacaoEnum.RegistroMedicacaoStatus;

import java.time.LocalDateTime;

public record RegistroMedicacaoDetalhe(
        Long id,
        String nomeMedicacao,
        LocalDateTime horarioAdministracao,
        String doseAdministrada,
        String observacao,
        RegistroMedicacaoStatus status,
        String nomeProfissional
) {
}
