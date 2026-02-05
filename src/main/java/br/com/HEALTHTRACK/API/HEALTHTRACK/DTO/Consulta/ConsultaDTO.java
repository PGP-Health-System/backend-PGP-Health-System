package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Consulta;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.RegistroMedicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ConsultaEnum.ConsultaStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ConsultaDTO(
    Paciente paciente,
    List<Long> registrosMedicacaoIds,
    String cpf,
    String medicoEmail,
    ProfissionalSaude profissionalSaude,
    LocalDateTime dataHoraAgendada,
    Integer duracaoMunutos,
    ConsultaStatus status, //ENUM
    String diagnostico,
    String conduta,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm
) {
}
