package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;


import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.EstadoCivil;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.Sexo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.TipoSanguinio;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "O nome do Paciente deve ter entre 5 e 100 caracteres!")
    private String nome;
    @NotNull
    @Past(message = "A data de nascimento do Paciente deve ser no passado!")
    private LocalDate dataNascimento;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Email
    @Column(unique = true)
    @NotNull
    private String email;
    @Column(unique = false)
    @NotNull
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter 11 digitos!")
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    @NotNull
    private Endereco endereco;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @Valid
    private List<Doenca> doencas;
    @ManyToOne
    @Valid
    private ProfissionalSaude profissionalSaude;
    @Column(unique = true)
    @NotNull
    @Valid
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 digitos!")
    private String cpf;
    @FutureOrPresent(message = "A data de cadastro precisa ser atual, ou futura!")
    private LocalDate dataCadastro;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoSanguinio tipoSanguinio;
    @NotNull
    @Size(min = 5, max = 100, message = "O nome da mae do Paciente deve conter entre 5 e 100 caracteres!")
    private String nomeMae;
    @Pattern(regexp = "\\d{15}", message = "O número do cartao do SUS deve conter 15 digitos!")
    private String numeroSus;
    private StatusPaciente statusPaciente;

    @Override
    public String toString() {
        return
                "Nome : '" + nome + '\'' +
                        ", Data de Nascimento : " + dataNascimento +
                        ", Sexo : " + sexo +
                        ", Profissional Responsável : " + profissionalSaude;
    }

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Doenca> getDoencas() {
        return doencas;
    }

    public void setDoencas(List<Doenca> doencas) {
        this.doencas = doencas;
    }

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoSanguinio getTipoSanguinio() {
        return tipoSanguinio;
    }

    public void setTipoSanguinio(TipoSanguinio tipoSanguinio) {
        this.tipoSanguinio = tipoSanguinio;
    }

    public String getNumeroSus() {
        return numeroSus;
    }

    public void setNumeroSus(String numeroSus) {
        this.numeroSus = numeroSus;
    }

    public StatusPaciente getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(StatusPaciente statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
}

