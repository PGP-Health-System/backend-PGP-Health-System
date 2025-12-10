package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.GravidadeAlergia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.TipoAlergia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "alergia")
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoAlergia tipo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GravidadeAlergia gravidade;

    @NotNull
    private LocalDate dataIdentificacao;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_saude_id")
    private ProfissionalSaude profissionalSaude;

    private boolean ativo = true;

    @Override
    public String toString() {
        return nome + " (" + tipo + ", " + gravidade + ")";
    }
}
