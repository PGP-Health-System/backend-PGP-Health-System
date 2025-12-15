package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente convertePacienteEntidadeCadastro(PacienteCadastroDTO pacienteCadastroDTO);
    Paciente convertePacienteEntidadeAtualizacao(PacienteAtualizacaoDTO pacienteAtualizacaoDTO);
    Paciente convertePacienteEntidadeAtualizacaoParcial(PacienteAtualizacaoParcialDTO pacienteAtualizacaoParcialDTO);
    Paciente convertePacienteEntidadeDetalhe(PacienteDetalhesDTO pacienteDetalhesDTO);
    Paciente convertePacienteEntidadeResumo(PacienteResumoDTO pacienteResumoDTO);

    PacienteDetalhesDTO converterPacienteDtoDetalhes(Paciente paciente);
    PacienteResumoDTO converterPacienteResumoDto(Paciente paciente);
}
