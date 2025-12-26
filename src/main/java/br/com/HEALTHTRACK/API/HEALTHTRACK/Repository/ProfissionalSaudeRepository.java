package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {


    Optional<ProfissionalSaude> findByEmail(String email);

}
