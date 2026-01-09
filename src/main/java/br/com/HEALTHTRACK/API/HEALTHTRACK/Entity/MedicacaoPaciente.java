    package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Pattern;
    import lombok.*;

    import java.time.LocalDate;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Entity
    @Table(name = "medicacao_paciente")
    public class MedicacaoPaciente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        private Long id;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "paciente_id")
        private Paciente paciente;

        @ManyToOne
        @JoinColumn(name = "medicacao_id")
        private Medicacao medicacao;

        @NotBlank
        private String dosagem;

        private String viaAdministracao;

        private String frequencia;

        private String instrucoes;

        private LocalDate dataInicio;

        private LocalDate dataFim;

        private boolean ativo = true;

        @ManyToOne
        @JoinColumn(name = "prescrito_por_id")
        private ProfissionalSaude prescritoPor;

        @ManyToOne
        @JoinColumn(name = "tratamento_id")
        private Tratamento tratamento;
    }
