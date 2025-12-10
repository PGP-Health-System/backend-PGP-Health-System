package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.TipoTratamento;
import jakarta.persistence.*;
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
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoTratamento tipo;

    @NotNull
    private LocalDate dataInicio;

    private LocalDate dataFim;

    private String frequencia;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTratamento status;

    @ManyToOne
    private Doenca doenca;

    @ManyToOne
    private ProfissionalSaude profissionalSaude;

    @OneToMany(mappedBy = "tratamento", cascade = CascadeType.ALL)
    private List<Medicacao> medicacoes;
}
