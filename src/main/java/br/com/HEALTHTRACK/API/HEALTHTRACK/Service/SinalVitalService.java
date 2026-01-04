package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.SinaisVitais.SinalVitalDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.SinalVital;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.PacienteEnum.StatusPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.SinalVital.SinalViltalNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.SinalVital.SinalVitalInvalido;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.SinalVital.SinalVitalNegativo;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.SinalVital.SinalVitalMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.SinalVitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SinalVitalService {

    private final SinalVitalRepository sinalVitalRepository;
    private final SinalVitalMapper sinalVitalMapper;
    private final PacienteRepository pacienteRepository;

    public SinalVitalService(SinalVitalRepository sinalVitalRepository, SinalVitalMapper sinalVitalMapper, PacienteRepository pacienteRepository) {
        this.sinalVitalRepository = sinalVitalRepository;
        this.sinalVitalMapper = sinalVitalMapper;
        this.pacienteRepository = pacienteRepository;
    }

    public SinalVitalDetalhesDTO registrarSinalVital(SinalVitalCadastroDTO dto){

        Paciente pacienteLocalizado = getPacienteByCpf(dto.cpfPaciente());

        boolean pacienteAtivo = isPacienteInativo(dto.cpfPaciente());
        if(pacienteAtivo){
            throw new PacienteNaoLocalizado("Não é possível registrar sinais vitais para um paciente inativo com o CPF: " + dto.cpfPaciente());
        }

        if(dto.alturaM() < 0 || dto.frequenciaCardiaca() < 0 || dto.frequenciaRespiratoria() < 0 || dto.pesoKg() < 0 || dto.pressaoDiastolica() < 0 || dto.pressaoSistolica() < 0 || dto.saturacao() < 0 || dto.temperaturaCelsius() < 0){
            throw new SinalVitalNegativo("Os sinais vitais não podem ter valores negativos.");
        }

        if(dto.saturacao() < dto.pressaoDiastolica()){
            throw new SinalVitalInvalido("A saturação não pode ser menor que a pressão diastólica.");
        }

        if(dto.saturacao() > 100){
            throw new SinalVitalInvalido("A saturação deve estar entre 0 e 100.");
        }

        SinalVital sinalVitalPaciente = sinalVitalMapper.toEntity(dto);
        sinalVitalPaciente.setPaciente(pacienteLocalizado);
        SinalVital sinalVitalSalvo = sinalVitalRepository.save(sinalVitalPaciente);

        return sinalVitalMapper.toDetalhesDTO(sinalVitalSalvo);
    }

    public SinalVitalDetalhesDTO buscarPorId(Long id){
        SinalVital sinalVital = sinalVitalRepository.findById(id)
                .orElseThrow(() -> new SinalViltalNaoLocalizado("Sinal vital não encontrado com o ID: " + id));

        return sinalVitalMapper.toDetalhesDTO(sinalVital);
    }

    public List<SinalVital> listarSinalVitalPorPaciente(String cpf){

        Optional<Paciente> pacienteLocalizado = pacienteRepository.findByCpf(cpf);
        if(pacienteLocalizado.isEmpty()){
            throw new PacienteNaoLocalizado("Paciente não encontrado para o CPF: " + cpf);
        }

        return sinalVitalRepository.findByPacienteCpf(cpf);
    }

    public List<SinalVital> listarSinalVitalPorPeriodo(LocalDate inicio, LocalDate fim, String cpf){

        Optional<Paciente> pacienteLocalizado = pacienteRepository.findByCpf(cpf);
        if(pacienteLocalizado.isEmpty()){
            throw new PacienteNaoLocalizado("Paciente não encontrado para o CPF: " + cpf);
        }

        if(fim.isBefore(inicio)){
            throw new SinalVitalInvalido("A data final não pode ser anterior à data inicial.");
        }

        return sinalVitalRepository.findByPacienteCpf(cpf).stream()
                .filter(sinalVital -> {
                    LocalDate dataRegistro = sinalVital.getRegistradoEm().toLocalDate();
                    return (dataRegistro.isEqual(inicio) || dataRegistro.isAfter(inicio)) &&
                           (dataRegistro.isEqual(fim) || dataRegistro.isBefore(fim));
                })
                .toList();
    }

    public SinalVitalDetalhesDTO buscarUltimoRegistro(String cpf){

        Optional<Paciente> pacienteLocalizado = pacienteRepository.findByCpf(cpf);
        if(pacienteLocalizado.isEmpty()){
            throw new PacienteNaoLocalizado("Paciente não encontrado para o CPF: " + cpf);
        }

        List<SinalVital> sinaisVitais = sinalVitalRepository.findByPacienteCpf(cpf);
        SinalVital ultimoSinalVital = sinaisVitais.stream()
                .max(Comparator.comparing(SinalVital::getRegistradoEm))
                .orElseThrow(() -> new SinalViltalNaoLocalizado("Nenhum sinal vital encontrado para o paciente com CPF: " + cpf));

        return sinalVitalMapper.toDetalhesDTO(ultimoSinalVital);
    }

    private boolean isPacienteInativo(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> paciente.getStatusPaciente() != StatusPaciente.ATIVO)
                .orElse(true);
    }

    private Paciente getPacienteByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new PacienteNaoLocalizado("Paciente não encontrado para o CPF: " + cpf));
    }
}
