package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.GravidadeSintoma;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum.TipoSintoma;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sintoma")
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GravidadeSintoma gravidade;

    @Enumerated(EnumType.STRING)
    private TipoSintoma tipo;

    @NotNull
    private LocalDate dataInicio;

    private String duracao;

    @ManyToOne
    private Doenca doenca;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private ProfissionalSaude profissionalSaude;

    @Override
    public String toString() {
        return "Sintoma{id=" + id + ", nome='" + nome + "', gravidade=" + gravidade + '}';
    }
}
