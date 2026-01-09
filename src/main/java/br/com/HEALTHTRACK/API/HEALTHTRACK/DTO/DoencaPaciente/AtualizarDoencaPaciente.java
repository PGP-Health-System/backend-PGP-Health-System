package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.HistoricoFamiliar;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.Gravidade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.StatusDoenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.TipoCronicidade;

import java.util.List;

public record AtualizarDoencaPaciente(
        TipoCronicidade tipoCronicidade,
        Gravidade gravidade,
        StatusDoenca status,
        List<TratamentoDTO> tratamentos,
        List<SintomaDTO> sintomas,
        List<MedicacaoDTO> medicacoes,
        List<AlergiaDTO> alergias,
        List<HistoricoFamiliar> historicoFamiliar,
        String observacaoMedica,
        DoencaDTO doenca
) {
}
