package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlertaSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertaSaudeRepository extends JpaRepository<AlertaSaude, Long> {
    Optional<AlertaSaude> findByPaciente_Cpf(String cpf);
}
