package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.RegistroMedicacaoEnum.RegistroMedicacaoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "registro_medicacao")
public class RegistroMedicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medicacao_paciente_id")
    private MedicacaoPaciente medicacaoPaciente;

    @NotNull
    private LocalDateTime horarioAdministracao;

    @ManyToOne
    @JoinColumn(name = "administrado_por_profissional_id")
    private ProfissionalSaude administradoPorProfissional;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "administrado_por_usuario_id")
    private Usuario administradoPorUsuario;

    private String doseAdministrada;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private RegistroMedicacaoStatus status = RegistroMedicacaoStatus.MARCADA;

    @PrePersist
    public void prePersist() {
        if (this.horarioAdministracao == null) {
            this.horarioAdministracao = LocalDateTime.now();
        }
    }
}
