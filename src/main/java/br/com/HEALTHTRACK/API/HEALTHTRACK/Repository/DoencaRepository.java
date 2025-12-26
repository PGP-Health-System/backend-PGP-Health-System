package br.com.HEALTHTRACK.API.HEALTHTRACK.Repository;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long> {

    <T> T findByCodigoCid(String codigoCid, Class<T> type);

    DoencaCadastroDTO findByNomeDoenca(String nomeDoenca);

}
