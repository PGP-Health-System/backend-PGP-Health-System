package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);

    Paciente findByEmail(String email);

    Optional<Paciente> findById(Long id);

    boolean existsByCpf(@NotBlank String cpf);

    boolean existsByEmail(@NotBlank String email);

    List<Paciente> findByCpfIn(List<String> cpf);

    @Query("""
                SELECT p
                FROM Paciente p
                JOIN p.profissionalSaude ps
                WHERE ps.nome = :nome
            """)
    List<Paciente> getByName(@Param("nome") String nome);

    void deleteByNome(String nome);
}
