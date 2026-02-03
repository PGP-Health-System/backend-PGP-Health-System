package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.RegistroMedicacaoEnum.RegistroMedicacaoStatus;

import java.time.LocalDateTime;

public record RegistroMedicacaoDTO(

        String cpfPaciente,
        String numeroMedicacao,
        String emailProfissional,
        LocalDateTime horarioAdministracao,
        String doseAdministrada,
        String observacao,
        RegistroMedicacaoStatus status
) {
}
