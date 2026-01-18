package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.ProfissionalSaude.ProfissionalSaudeDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.GravidadeSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.TipoSintoma;

import java.time.LocalDate;

public record DetalheSintomaDTO(
        String nome,
        String descricao,
        GravidadeSintoma gravidade,
        TipoSintoma tipo,
        LocalDate dataInicio,
        String duracao,
        DoencaDTO doenca,
        PacienteDTO paciente,
        ProfissionalSaudeDTO profissionalSaude) {
}
