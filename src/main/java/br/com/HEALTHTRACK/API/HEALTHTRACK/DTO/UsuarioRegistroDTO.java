package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.UsuarioEnum.Role;

public record UsuarioRegistroDTO(
        String email,
        String senha,
        Role role,
        ProfissionalSaude profissionalSaude,
        Paciente paciente
) {
}
