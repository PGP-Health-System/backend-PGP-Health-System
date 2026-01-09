package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.TratamentoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamentoPacienteRepository implements JpaRepository<Long, TratamentoPaciente> {
    TratamentoPaciente save(TratamentoPaciente tratamentoPaciente);
}
