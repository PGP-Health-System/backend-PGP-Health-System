package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO;

public record UsuarioLoginDTO(
        String email,
        String username,
        String senha
) {
}
