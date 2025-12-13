package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;

public record PacienteAtualizacaoParcialDTO(
        String telefone,
        Endereco endereco,
        EstadoCivil estadoCivil,
        ProfissionalSaude profissionalSaude,
        String numeroSus
) {
}
