package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.Frequencia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "medicacao")
public class Medicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String nomeMedicamento;

    @NotBlank
    @Size(max = 50)
    private String dosagem;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaFarmaceutica forma;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ViaAdministracao via;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Frequencia frequencia;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Size(max = 500)
    private String observacoes;

    private boolean ativo = true;

    @Override
    public String toString() {
        return nomeMedicamento + " " + dosagem + " (" + frequencia + ")";
    }
}
