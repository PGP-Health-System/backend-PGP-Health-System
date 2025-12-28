package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.EstagioDoenca;

import java.time.LocalDate;

public record DoencaDTO(
        String nomeDoenca,
        String codigoCid,
        LocalDate dataDiagnostico
) {
}
