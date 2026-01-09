package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TratamentoPacienteDTO(

        String cpf,

        String codigoTratamento,

        String codigoCid,

        String email,
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


) {
}
