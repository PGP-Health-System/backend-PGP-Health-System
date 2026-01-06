package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente;

public record AlergiaPacienteDetalheDTO(
        String cpfPaciente,
        String nomeAlergia,
        String dataIdentificacao,
        boolean confirmada,
        String observacao
) {
}
