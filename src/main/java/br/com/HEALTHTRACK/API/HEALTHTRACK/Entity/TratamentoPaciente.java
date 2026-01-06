package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tratamento_paciente")
public class TratamentoPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    private Tratamento tratamento;

    @ManyToOne
    @NotNull
    private Paciente paciente;

    @ManyToOne
    private Doenca doenca;

    @ManyToOne
    private ProfissionalSaude profissionalSaude;

    @NotNull
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTratamento status;
}
