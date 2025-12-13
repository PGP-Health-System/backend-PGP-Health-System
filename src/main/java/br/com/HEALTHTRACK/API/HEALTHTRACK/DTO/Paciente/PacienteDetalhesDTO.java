package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.TipoSanguinio;

import java.time.LocalDate;
import java.util.List;

public record PacienteDetalhesDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        Endereco endereco,
        Sexo sexo,
        LocalDate dataNascimento,
        TipoSanguinio tipoSanguinio,
        EstadoCivil estadoCivil,
        String nomeMae,
        String numeroSus,
        List<DoencaDTO> doencas,
        List<String> historicoFamiliar
) {
}
