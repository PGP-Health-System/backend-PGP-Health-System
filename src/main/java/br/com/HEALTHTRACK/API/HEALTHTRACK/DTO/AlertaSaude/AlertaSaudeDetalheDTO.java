package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlertaSaude;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaPrioridade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaTipo;

import java.time.LocalDate;

public record AlertaSaudeDetalheDTO(
        Long id,
        String cpf,
        AlertaTipo tipo,
        String descricao,
        AlertaPrioridade prioridade,
        LocalDate criadoEm,
        boolean resolvido,
        LocalDate resolvidoEm,
        String geradoPorNome
) {
}
