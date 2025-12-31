package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.AlergiaEnum.HistoricoFamiliar;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.EstagioDoenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.Gravidade;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.StatusDoenca;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.DoencaEnum.TipoCronicidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "doenca_paciente")
public class    DoencaPaciente {

    // Esta entidade sera responsavel por vincular as doencas aos pacientes, permitindo um relacionamento muitos-para-muitos
    // Alem disso, podera conter informacoes adicionais sobre a doenca no contexto do paciente
    // Pedro Borba -- Since 20-DEZ-2025

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCronicidade cronicidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gravidade gravidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusDoenca status;

    private LocalDate dataDiagnostico;
    private LocalDate dataInicioSintomas;
    private LocalDate dataUltimaAtualizacao;

    @OneToMany
    @NotNull
    private List<Tratamento> tratamentos;

    @OneToMany
    @NotNull
    private List<Sintoma> sintomas;

    @OneToMany
    @NotNull
    private List<Medicacao> medicacoes;

    @OneToMany
    @NotNull
    private List<Alergia> alergias;

    @NotNull

    private List<HistoricoFamiliar> historicoFamiliar;

    @Column(length = 2000)
    @NotBlank
    private String observacoesMedicas;

    @ManyToOne
    @NotNull
    private Paciente paciente;

    @NotNull
    private EstagioDoenca estagio;
}
