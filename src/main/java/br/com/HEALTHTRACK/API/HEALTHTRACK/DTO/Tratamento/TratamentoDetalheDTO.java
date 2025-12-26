package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.TipoTratamento;

import java.time.LocalDate;
import java.util.List;

public record TratamentoDetalheDTO(
        String nome,
        String descricao,
        TipoTratamento tipo,
        LocalDate dataInicio,
        LocalDate dataFim,
        String frequencia,
        StatusTratamento status,
        Doenca doenca,
        ProfissionalSaude profissionalSaude,
        List<Medicacao> medicacoes
) {
}
