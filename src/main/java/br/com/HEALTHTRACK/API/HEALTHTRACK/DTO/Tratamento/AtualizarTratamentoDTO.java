package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;

import java.time.LocalDate;

public record AtualizarTratamentoDTO(
        String nome,
        String codigoTratamento,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        String frequencia,
        StatusTratamento status
) {
}
