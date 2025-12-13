package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar;

import java.util.List;

public record HistoricoFamiliarDTO(
        List<String> condicao,
        String observacao
) {
}
