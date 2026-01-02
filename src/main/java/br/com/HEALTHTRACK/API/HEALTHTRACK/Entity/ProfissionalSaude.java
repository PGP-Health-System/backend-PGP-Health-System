package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ProfissionalSaudeEnum.Especialidade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ProfissionalSaudeEnum.TipoRegistroProfissional;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "profissional_saude")
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nome;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Especialidade> especialidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoRegistroProfissional tipoRegistro;

    @NotBlank
    private String registroProfissional;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    private LocalDate dataCadastro = LocalDate.now();

    private boolean ativo = true;

    @OneToMany(mappedBy = "profissionalSaude")
    private List<Paciente> pacientes;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @Override
    public String toString() {
        return nome + " (" + especialidade + ")";
    }
}
