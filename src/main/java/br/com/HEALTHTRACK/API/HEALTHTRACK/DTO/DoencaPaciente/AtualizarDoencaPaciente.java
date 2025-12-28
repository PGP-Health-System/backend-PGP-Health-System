package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar.HistoricoFamiliarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.PacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.EstagioDoenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.Gravidade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.StatusDoenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.TipoCronicidade;

import java.time.LocalDate;
import java.util.List;

public record AtualizarDoencaPaciente(
        TipoCronicidade tipoCronicidade,
        Gravidade gravidade,
        StatusDoenca status,
        List<TratamentoDTO> tratamentos,
        List<SintomaDTO> sintomas,
        List<MedicacaoDTO> medicacoes,
        List<AlergiaDTO> alergias,
        List<HistoricoFamiliarDTO> historicoFamiliar,
        String observacaoMedica,
        EstagioDoenca estagio
) {
}
