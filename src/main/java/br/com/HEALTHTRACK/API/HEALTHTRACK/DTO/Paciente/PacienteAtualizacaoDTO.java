package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;

import java.time.LocalDate;
import java.util.List;

public record PacienteAtualizacaoDTO(
        String nome,
        String telefone,
        Endereco endereco,
        Sexo sexo,
        EstadoCivil estadoCivil,
        String nomeMae,
        String numeroSus,
        List<Doenca> doencas,
        List<String> historicoFamiliar
) {
}
