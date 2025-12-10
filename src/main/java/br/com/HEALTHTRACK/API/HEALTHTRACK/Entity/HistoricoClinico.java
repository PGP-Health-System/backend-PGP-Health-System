package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "historico_clinico")
public class HistoricoClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(length = 4000)
    private String resumoClinico;

    @Column(length = 4000)
    private String antecedentesMedicos;

    @Column(length = 4000)
    private String antecedentesCirurgicos;

    @Column(length = 4000)
    private String historicoFamiliar;

    @Column(length = 2000)
    private String vacinacoes;

    @ElementCollection
    private List<String> anexos;

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
