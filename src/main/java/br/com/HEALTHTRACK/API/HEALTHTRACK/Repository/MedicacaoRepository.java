package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicacaoRepository extends JpaRepository<Medicacao,Long> {

    Optional<Medicacao> findByCodigoMedicamento(String codigoMedicamento);

    Optional<List<Medicacao>> findAllByCodigoMedicamento(String codigoMedicamento);

    Medicacao findByNomeMedicamento(String nomeMedicamento);

    @Query("""
    select m 
    from Medicacao m
    where lower(m.nomeMedicamento) like lower(concat('%', :termo, '%'))
    order by m.nomeMedicamento
    """)
    List<Medicacao> findByTermoMedicacao(@Param("termo") String termo);

    List<MedicacaoPaciente> findAllByPacienteCpf(String cpf);
}
