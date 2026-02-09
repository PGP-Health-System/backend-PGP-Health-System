package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoPacienteDetalhe;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Medicacao;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.MedicacaoPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.ProfissionalSaude;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicamento.MedicamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Profissional.EmailNaoEncontrado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.MedicacaoPaciente.MedicacaoPacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoPacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.MedicacaoRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.ProfissionalSaudeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicacaoPacienteSerivce {

    private MedicacaoPacienteRepository medicacaoPacienteRepository;
    private ProfissionalSaudeRepository profissionalSaudeRepository;
    private PacienteRepository pacienteRepository;
    private PacienteService pacienteService;
    private MedicacaoRepository medicacaoRepository;
    private MedicacaoPacienteMapper medicacaoPacienteMapper;

    public MedicacaoPacienteSerivce(MedicacaoPacienteRepository medicacaoPacienteRepository, ProfissionalSaudeRepository profissionalSaudeRepository, PacienteRepository pacienteRepository, PacienteService pacienteService, MedicacaoRepository medicacaoRepository, MedicacaoPacienteMapper medicacaoPacienteMapper) {
        this.medicacaoPacienteRepository = medicacaoPacienteRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.pacienteRepository = pacienteRepository;
        this.pacienteService = pacienteService;
        this.medicacaoRepository = medicacaoRepository;
        this.medicacaoPacienteMapper = medicacaoPacienteMapper;
    }

    public MedicacaoPacienteRepository getMedicacaoPacienteRepository() {
        return medicacaoPacienteRepository;
    }

    public MedicacaoPacienteDetalhe registraMedicacaoPaciente(MedicacaoPacienteDTO medicacaoPacienteDTO) {
        Paciente paciente = pacienteService.buscarPacientePorCpf(medicacaoPacienteDTO.pacienteCpf());

        ProfissionalSaude profissionalSaude = profissionalSaudeRepository
                .findByEmail(medicacaoPacienteDTO.emailProfissional())
                .orElseThrow(() -> new EmailNaoEncontrado("Profissional não encontrado com esse email"));

        Medicacao medicacao = medicacaoRepository.findByCodigoMedicamento(medicacaoPacienteDTO.codigoMedicamento())
                .orElseThrow(() -> new MedicamentoNaoLocalizado("Coódigo Cid informado incorreto"));

        MedicacaoPaciente medicacaoPaciente = medicacaoPacienteMapper.toEntity(medicacaoPacienteDTO);
        medicacaoPaciente.setMedicacao(medicacao);
        medicacaoPaciente.setPaciente(paciente);
        medicacaoPaciente.setPrescritoPor(profissionalSaude);

        medicacaoPacienteRepository.save(medicacaoPaciente);

        return medicacaoPacienteMapper.toDetalhe(medicacaoPaciente);
    }

    public List<MedicacaoPacienteDetalhe> buscarPorPaciente(String cpf) {
        return medicacaoRepository.findAllByPacienteCpf(cpf)
                .stream()
                .map(medicacaoPacienteMapper::toDetalhe)
                .toList();
    }

    public List<MedicacaoPacienteDetalhe> buscarAtivos() {
        return medicacaoPacienteRepository.findAtivos()
                .stream()
                .map(medicacaoPacienteMapper::toDetalhe)
                .toList();
    }

    public void desativarMedicacaoPaciente(Long id) {
        MedicacaoPaciente medicacaoPaciente = medicacaoPacienteRepository
                .findById(id)
                .orElseThrow(() -> new MedicamentoNaoLocalizado("Medicação não encontrada"));

        medicacaoPaciente.desativar(); // seta ativo=false ou dataFim=now
        medicacaoPacienteRepository.save(medicacaoPaciente);
    }



}
