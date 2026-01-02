package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Profissional;

import jakarta.validation.constraints.Pattern;


import java.util.List;

public record AdicionaDTO(
        List<@Pattern(regexp = "\\d{11}", message = "Cpf deve ter 11 digitos") String> cpf,
        String email
) {
}
