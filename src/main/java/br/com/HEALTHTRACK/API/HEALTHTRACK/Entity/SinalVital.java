package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

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
@Table(name = "sinal_vital")
public class SinalVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    private LocalDateTime registradoEm;

    private Double temperaturaCelsius;
    private Integer frequenciaCardiaca;
    private Integer frequenciaRespiratoria;
    private Integer pressaoSistolica;
    private Integer pressaoDiastolica;
    private Integer saturacao;
    private Double pesoKg;
    private Double alturaM;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "registrado_por_profissional_id")
    private ProfissionalSaude registradoPorProfissional;

    @ManyToOne
    @JoinColumn(name = "registrado_por_usuario_id")
    private Usuario registradoPorUsuario;

    @PrePersist
    public void prePersist() {
        if (registeredDefault()) {
            this.registradoEm = LocalDateTime.now();
        }
    }

    private boolean registeredDefault() {
        return this.registradoEm == null;
    }
}
