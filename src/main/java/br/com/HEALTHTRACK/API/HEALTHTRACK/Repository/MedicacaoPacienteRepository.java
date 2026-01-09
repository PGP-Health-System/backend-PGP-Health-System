package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;

@Repository
public interface MedicacaoPacienteRepository extends JpaRepository<MedicacaoPaciente, Long> {

    List<MedicacaoPaciente> findAllByMedicacao_codigoMedicamento(String s);
}
