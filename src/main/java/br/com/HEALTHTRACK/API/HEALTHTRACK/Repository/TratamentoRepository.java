package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {

    Optional<Tratamento> findByNome(String nome);

    Tratamento findByCodigoTratemento(String codigoTratamento);

   Optional<Tratamento> findByCodigoTratamento(String s);

    @Query("SELECT MAX(t.codigoTratamento) FROM Tratamento t")
    String findCodigoMax();
}
