package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicacaoRepository extends JpaRepository<Medicacao,Long> {

    Optional<Medicacao> findByCodigoMedicamento(String codigoMedicamento);

    Optional<List<Medicacao>> findAllByCodigoMedicamento(String codigoMedicamento);
}
