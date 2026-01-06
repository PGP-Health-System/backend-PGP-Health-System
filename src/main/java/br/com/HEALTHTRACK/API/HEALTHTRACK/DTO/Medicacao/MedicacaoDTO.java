package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.Frequencia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados da medicação")
public record MedicacaoDTO(
        String cpfPaciente,
        String nomeTratamento,
        String nomeMedicamento,
        String codigoMedicamento,
        String dosagem,
        FormaFarmaceutica forma,
        ViaAdministracao via,
        Frequencia frequencia,
        LocalDate dataInicio,
        LocalDate dataFim,
        Tratamento tratamentoId,
        Paciente pacienteId,
        String observacoes,
        boolean ativo
) {
}