package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TratamentoPacienteDTO(

        Long id,

        @NotNull
        Long tratamentoId,

        @NotNull
        Long pacienteId,

        @NotNull
        Long profissionalSaudeId,

        @NotNull
        Long doencaId,

        @NotNull
        LocalDate dataInicio,

        LocalDate dataFim,

        @NotNull
        StatusTratamento status,

        String observacoes

) {}
