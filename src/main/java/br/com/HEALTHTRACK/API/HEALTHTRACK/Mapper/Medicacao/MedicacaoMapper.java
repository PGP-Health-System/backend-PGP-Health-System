package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MedicacaoMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "dataInicio", ignore = true)
        @Mapping(target = "dataFim", ignore = true)
        @Mapping(target = "frequencia", ignore = true)
        @Mapping(target = "paciente", source = "paciente")
        @Mapping(target = "tratamento", source = "tratamento")
        Medicacao converteParaEntidade(
                MedicacaoDTO medicacaoDTO,
                Paciente paciente,
                Tratamento tratamento
        );

        MedicacaoDetalheDTO converteEntidadeParaDetalheDTO(Medicacao medicacao);

    }