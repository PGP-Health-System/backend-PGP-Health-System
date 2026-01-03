package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

    Optional<Alergia> findByNome(String nome);

    List<Alergia> findByAtivoTrue();
}
