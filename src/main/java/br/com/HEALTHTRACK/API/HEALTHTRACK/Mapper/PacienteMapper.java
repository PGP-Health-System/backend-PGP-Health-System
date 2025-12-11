package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PacienteMapper {

    Paciente convertePacienteEntidadeCadastro(PacienteCadastroDTO pacienteCadastroDTO);
    Paciente convertePacienteEntidadeAtualizacao(PacienteAtualizacaoDTO pacienteAtualizacaoDTO);
    Paciente convertePacienteEntidadeAtualizacaoParcial(PacienteAtualizacaoParcialDTO pacienteAtualizacaoParcialDTO);
    Paciente convertePacienteEntidadeDetalhe(PacienteDetalhesDTO pacienteDetalhesDTO);
    Paciente convertePacienteEntidadeResumo(PacienteResumoDTO pacienteResumoDTO);
}
