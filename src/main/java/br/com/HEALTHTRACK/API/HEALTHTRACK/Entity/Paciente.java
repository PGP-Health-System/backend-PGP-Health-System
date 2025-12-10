package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;


import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.TipoSanguinio;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;
    @NotNull
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
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Doenca> doencas;
    @ManyToOne
    private ProfissionalSaude profissionalSaude;
    @Column(unique = true)
    @NotNull
    private String cpf;
    private LocalDate dataCadastro;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoSanguinio tipoSanguinio;
    @NotNull
    private String nomeMae;
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

