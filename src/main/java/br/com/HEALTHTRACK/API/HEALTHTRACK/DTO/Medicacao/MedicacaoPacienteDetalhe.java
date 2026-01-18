package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;

import java.time.LocalDate;

public record MedicacaoPacienteDetalhe(
        Paciente paciente,
        Medicacao medicacao,
        String dosagem,
        String viaAdministracao,
        String frequencia,
        String instrucoes,
        LocalDate dataInicio,
        LocalDate dataFim,
        boolean ativo,
        ProfissionalSaude prescritoPor,
        Tratamento tratamento
) {
}
