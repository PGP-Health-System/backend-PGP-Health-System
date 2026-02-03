package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.RegistroMedicacao.RegistroMedicacaoDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.MedicacaoPaciente.PacienteMedicacaoNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicamento.MedicamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.RegistroMedicacao.RegistroMedicacaoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroMedicacaoService {


    private RegistroMedicacaoRepository registroMedicacaoRepository;
    private  PacienteRepository pacienteRepository;
    private  MedicacaoRepository medicacaoRepository;
    private  MedicacaoPacienteRepository medicacaoPacienteRepository;
    private  ProfissionalSaudeRepository profissionalSaudeRepository;
    private RegistroMedicacaoMapper registroMedicacaoMapper;

    public RegistroMedicacaoService(RegistroMedicacaoRepository registroMedicacaoRepository, PacienteRepository pacienteRepository, MedicacaoRepository medicacaoRepository, MedicacaoPacienteRepository medicacaoPacienteRepository, ProfissionalSaudeRepository profissionalSaudeRepository, RegistroMedicacaoMapper registroMedicacaoMapper) {
        this.registroMedicacaoRepository = registroMedicacaoRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicacaoRepository = medicacaoRepository;
        this.medicacaoPacienteRepository = medicacaoPacienteRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.registroMedicacaoMapper = registroMedicacaoMapper;
    }

    public RegistroMedicacaoDetalhe registrarMedicacao(RegistroMedicacaoDTO registroMedicacaoDTO){

        Paciente paciente = pacienteRepository.findByCpf(registroMedicacaoDTO.cpfPaciente())
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não encontrado pelo cpf"));

        Medicacao medicacao = medicacaoRepository.findByCodigoMedicamento(registroMedicacaoDTO.numeroMedicacao())
                .orElseThrow(() -> new MedicamentoNaoLocalizado("Medicação não encontrada pelo numero do medicamento"));

        MedicacaoPaciente medicacaoPaciente =
                medicacaoPacienteRepository
                        .findByPacienteAndMedicacao(paciente, medicacao)
                        .orElseThrow(() -> new PacienteMedicacaoNaoEncontrado(
                                "Medicação não vinculada ao paciente"
                        ));

        ProfissionalSaude profissional = profissionalSaudeRepository
                .findByEmail(registroMedicacaoDTO.emailProfissional())
                .orElseThrow(() -> new EmailNaoEncontrado(
                        "Profissional de saúde não encontrado pelo email"
                ));

        RegistroMedicacao registroMedicacao = registroMedicacaoMapper.toEntity(registroMedicacaoDTO);
        registroMedicacao.setMedicacaoPaciente(medicacaoPaciente);
        registroMedicacao.setAdministradoPorProfissional(profissional);

        registroMedicacaoRepository.save(registroMedicacao);

       return new RegistroMedicacaoDetalhe (
                registroMedicacao.getId(),
                registroMedicacao.getMedicacaoPaciente().getMedicacao().getNomeMedicamento(),
                registroMedicacao.getHorarioAdministracao(),
                registroMedicacao.getDoseAdministrada(),
                registroMedicacao.getObservacao(),
                registroMedicacao.getStatus(),
                registroMedicacao.getAdministradoPorProfissional().getNome()
        );
    }





}
