package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.HistoricoFamiliar.HistoricoFamiliarDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.TipoSanguinio;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record PacienteCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Column(unique = true)
        String cpf,
        @NotBlank
        @Column(unique = true)
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Endereco endereco,
        @NotNull
        Sexo sexo,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        TipoSanguinio tipoSanguinio,
        @NotNull
        EstadoCivil estadoCivil,
        @NotBlank
        String nomeMae,
        String numeroSus,
        List<Doenca> doencas,
        List<HistoricoFamiliarDTO> historicoFamiliar
) {
}
