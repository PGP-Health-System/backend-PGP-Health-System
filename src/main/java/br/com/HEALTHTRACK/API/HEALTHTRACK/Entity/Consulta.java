package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.ConsultaEnum.ConsultaStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "profissional_saude_id")
    private ProfissionalSaude profissionalSaude;

    @NotNull
    private LocalDateTime dataHoraAgendada;

    private Integer duracaoMinutos;

    @Enumerated(EnumType.STRING)
    private ConsultaStatus status = ConsultaStatus.MARCADA;

    @Size(max = 1000)
    private String motivo;

    @Size(max = 4000)
    private String anamnese;

    @Size(max = 4000)
    private String diagnostico;

    @Size(max = 4000)
    private String conduta;

    @OneToMany
    @JoinColumn(name = "consulta_id")
    private List<RegistroMedicacao> registrosMedicacao;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
