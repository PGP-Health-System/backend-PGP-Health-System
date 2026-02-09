package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicacaoPacienteRepository extends JpaRepository<MedicacaoPaciente, Long> {

    List<MedicacaoPaciente> findAllByMedicacao_codigoMedicamento(String s);

  Optional<MedicacaoPaciente> findByPacienteAndMedicacao(Paciente paciente, Medicacao medicacao);

    @Query(
            value = """
            SELECT DISTINCT p.*
            FROM paciente p
            JOIN medicacao_paciente mp ON mp.paciente_id = p.id
            WHERE mp.ativo = true
        """,
            nativeQuery = true
    )
  List<MedicacaoPaciente> findAtivos();
}
