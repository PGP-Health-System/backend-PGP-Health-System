package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaPrioridade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaTipo;

import java.time.LocalDate;

public record AlertaSaudeResumoDTO(
        Long id,
        AlertaTipo tipo,
        AlertaPrioridade prioridade,
        boolean resolvido,
        LocalDate criadoEm
) {
}
