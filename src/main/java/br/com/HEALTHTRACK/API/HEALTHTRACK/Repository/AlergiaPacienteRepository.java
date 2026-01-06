package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.AlergiaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlergiaPacienteRepository extends JpaRepository<AlergiaPaciente, Long> {
    boolean existsByPacienteCpfAndAlergiaNome(String cpfPaciente, String nomeAlergia);

    List<AlergiaPaciente> findAllByPacienteId(long id);

    List<AlergiaPaciente> findByPacienteIdAndConfirmadaTrue(long id);
}
