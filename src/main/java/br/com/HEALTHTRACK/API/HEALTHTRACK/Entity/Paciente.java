package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;


import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.TipoSanguinio;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "O nome do Paciente deve ter entre 5 e 100 caracteres!")
    private String nome;
    @NotNull
    @Past(message = "A data de nascimento do Paciente deve ser no passado!")
    private LocalDate dataNascimento;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Email
    @Column(unique = true)
    @NotNull
    private String email;
    @Column(unique = false)
    @NotNull
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter 11 digitos!")
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    @NotNull
    private Endereco endereco;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @Valid
    private List<Doenca> doencas;
    @ManyToOne
    @Valid
    private ProfissionalSaude profissionalSaude;
    @Column(unique = true)
    @NotNull
    @Valid
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 digitos!")
    private String cpf;
    @FutureOrPresent(message = "A data de cadastro precisa ser atual, ou futura!")
    private LocalDate dataCadastro;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoSanguinio tipoSanguinio;
    @NotNull
    @Size(min = 5, max = 100, message = "O nome da mae do Paciente deve conter entre 5 e 100 caracteres!")
    private String nomeMae;
    @Pattern(regexp = "\\d{15}", message = "O número do cartao do SUS deve conter 15 digitos!")
    private String numeroSus;
    private StatusPaciente statusPaciente;

    @Override
    public String toString() {
        return
                        "Nome : '" + nome + '\'' +
                        ", Data de Nascimento : " + dataNascimento +
                        ", Sexo : " + sexo +
                        ", Profissional Responsável : " + profissionalSaude;
    }

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDate.now();
    }

}

