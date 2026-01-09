package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.StatusTratamento;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.TratamentoEnum.TipoTratamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String codigoTratamento;

    @NotBlank
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoTratamento tipo;

    private String frequencia;

    @OneToOne(mappedBy = "tratamentoPaciente", cascade = CascadeType.ALL)
    private TratamentoPaciente tratamentoPaciente;

}
