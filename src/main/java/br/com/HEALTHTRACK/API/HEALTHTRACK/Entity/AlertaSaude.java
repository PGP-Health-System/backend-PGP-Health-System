package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaPrioridade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlertaSaudeEnum.AlertaTipo;
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
@Table(name = "alerta_saude")
public class AlertaSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AlertaTipo tipo;

    @NotNull
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AlertaPrioridade prioridade = AlertaPrioridade.MEDIA;

    private LocalDateTime criadoEm;

    private boolean resolvido = false;

    private LocalDateTime resolvidoEm;

    @ManyToOne
    @JoinColumn(name = "gerado_por_usuario_id")
    private Usuario geradoPorUsuario;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}
