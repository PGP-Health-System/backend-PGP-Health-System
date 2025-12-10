package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.HistoricoFamiliarEnum.Parentesco;
import jakarta.persistence.*;

@Entity
@Table(name = "historico_familiar")
public class HistoricoFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @Column(nullable = false)
    private String condicao;

    private Integer idadeDiagnostico;
    private boolean falecido;
    private String observacao;
}
