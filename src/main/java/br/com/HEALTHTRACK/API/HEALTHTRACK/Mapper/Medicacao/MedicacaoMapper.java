package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Medicacao;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.AtualizarMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDetalheDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MedicacaoMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "dataInicio", ignore = true)
        @Mapping(target = "dataFim", ignore = true)
        @Mapping(target = "frequencia", ignore = true)
        @Mapping(target = "paciente", source = "paciente")
        @Mapping(target = "tratamento", source = "tratamento")
        @Mapping(target = "medicacao", ignore = true)
        @Mapping(target = "viaAdministracao", ignore = true)
        @Mapping(target = "instrucoes", ignore = true)
        @Mapping(target = "prescritoPor", ignore = true)
        MedicacaoPaciente converteParaEntidade(
                MedicacaoDTO medicacaoCadastroDTO,
                Paciente paciente,
                Tratamento tratamento
        );
       @Mapping(target = "id", ignore = true)
       @Mapping(target = "ativo", ignore = true)
       Medicacao toEntityCadastroMedicacao(MedicacaoCadastroDTO medicacaoCadastroDTO);

        MedicacaoDetalheDTO converteEntidadeParaDetalheDTO(Medicacao medicacao);
    }