package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.Frequencia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicacao_paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicacaoPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medicacao_id", nullable = false)
    private Medicacao medicacao;

    @NotBlank
    private String dosagem;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ViaAdministracao viaAdministracao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Frequencia frequencia;

    private String instrucoes;

    @NotNull
    private LocalDate dataInicio;

    private LocalDate dataFim;

    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "prescrito_por_id")
    private ProfissionalSaude prescritoPor;

    @ManyToOne
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;
}

