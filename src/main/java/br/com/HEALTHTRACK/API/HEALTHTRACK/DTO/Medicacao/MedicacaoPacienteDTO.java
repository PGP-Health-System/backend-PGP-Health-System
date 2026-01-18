package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;

import java.time.LocalDate;

public record MedicacaoPacienteDTO(
        String pacienteCpf,
        String codigoMedicamento,
        String emailProfissional,
        String dosagem,
        String viaAdministracao,
        String frequencia,
        String instrucoes,
        LocalDate dataInicio,
        LocalDate dataFim,
        boolean ativo,
        Tratamento tratamento
) {
}
