package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais;

import java.time.LocalDateTime;

public record SinalVitalDetalhesDTO(
        String cpfPaciente,
        LocalDateTime registradoEm,
        Double temperaturaCelsius,
        Integer frequenciaCardiaca,
        Integer frequenciaRespiratoria,
        Integer pressaoSistolica,
        Integer pressaoDiastolica,
        Integer saturacao,
        Double pesoKg,
        Double alturaM,
        String observacao,
        String nomeProfissionalSaude
) {
}
