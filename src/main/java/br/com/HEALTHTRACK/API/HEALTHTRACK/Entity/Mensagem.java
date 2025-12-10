package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @NotBlank
    @Column(length = 4000)
    private String conteudo;

    private String assunto;

    private LocalDateTime enviadoEm;

    private LocalDateTime lidoEm;

    private boolean lido = false;

    @PrePersist
    public void prePersist() {
        this.enviadoEm = LocalDateTime.now();
    }
}
