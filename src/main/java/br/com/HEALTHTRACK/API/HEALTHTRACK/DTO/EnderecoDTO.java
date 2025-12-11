package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.EnderecoEnum.UF;

public record EnderecoDTO(

        Long id,
        String logradouro,
        String bairro,
        String cidade,
        UF estado,
        String cep,
        String complemento,
        String numero

) {
}
