package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.FormaFarmaceutica;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.Frequencia;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.MedicacaoEnum.ViaAdministracao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(
        name = "medicacao",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nome_medicamento", "forma"})
        }
)
public class Medicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(unique = true)
    private String nomeMedicamento;

    @Pattern(regexp = "^[\\p{L}0-9 ]+$")
    @Column(unique = true)
    private String codigoMedicamento;

    @NotBlank
    @Size(max = 50)
    private String dosagemPadrao;

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


    @Size(max = 500)
    private String observacoes;

    private boolean ativo = true;
}


