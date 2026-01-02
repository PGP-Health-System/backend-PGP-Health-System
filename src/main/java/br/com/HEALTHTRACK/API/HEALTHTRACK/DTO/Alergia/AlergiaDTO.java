package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.ProfissionalSaude.ProfissionalSaudeDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.GravidadeAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.TipoAlergia;

import java.time.LocalDate;

public record AlergiaDTO(
        String nome,
        String descricao,
        TipoAlergia tipo,
        GravidadeAlergia gravidade,
        LocalDate dataIdentificacao,
        PacienteDTO paciente,
        ProfissionalSaudeDTO profissionalSaude
) {
}
