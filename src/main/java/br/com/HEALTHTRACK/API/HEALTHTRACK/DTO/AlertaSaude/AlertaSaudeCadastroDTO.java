package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaPrioridade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaTipo;

public record AlertaSaudeCadastroDTO(
        String pacienteCpf,
        AlertaTipo tipo,
        String descricao,
        AlertaPrioridade prioridade
) {
}
