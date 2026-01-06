package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.AlergiaPaciente;

public record AlergiaPacienteCadastroDTO(
        String cpfPaciente,
        String nomeAlergia,
        boolean confirmada,
        String observacao
) {
}
