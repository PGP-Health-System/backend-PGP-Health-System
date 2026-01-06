package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alergia_paciente")
public class AlergiaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Alergia alergia;
    @PastOrPresent
    private LocalDate dataIdentificacao;
    @PastOrPresent
    private LocalDate dataAtualizacao;
    @NotNull
    private boolean confirmada;
    private String observacao;
}
