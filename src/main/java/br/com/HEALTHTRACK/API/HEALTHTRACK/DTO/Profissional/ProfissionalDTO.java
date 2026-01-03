package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ProfissionalSaudeEnum.Especialidade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ProfissionalSaudeEnum.TipoRegistroProfissional;

import java.util.List;

public record ProfissionalDTO(
        List<String> cpfs,
        String nome,
        List<Especialidade> especialidade,
        TipoRegistroProfissional tipoRegistro,
        String registroProfissional,
        String email,
        String telefone,
        String cpf
) {
}
