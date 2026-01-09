package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Alergia.AlergiaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.AtualizarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.CadastrarDoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.DoencaPaciente.DetalhesDoencaPacienteDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Medicacao.MedicacaoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Sintoma.SintomaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Alergia.AlergiaNaoLocalizada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.DoencaDuplicada;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca.NomeDoencaNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicamento.MedicamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente.PacienteNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento.TratamentoNaoLocalizado;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Alergia.AlergiaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.DoencaPaciente.DoencaPacienteMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Medicacao.MedicacaoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Sintoma.SintomaMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento.TratamentoMapper;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaPacienteRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.DoencaRepository;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoencaPacienteService{

    private final DoencaRepository doencaRepository;
    private final DoencaPacienteMapper doencaPacienteMapper;
    private final PacienteRepository pacienteRepository;
    private final DoencaPacienteRepository doencaPacienteRepository;
    private final MedicacaoMapper medicacaoMapper;
    private final TratamentoMapper tratamentoMapper;
    private final SintomaMapper sintomaMapper;
    private final AlergiaMapper alergiaMapper;
    private final DoencaService doencaService;

    public DoencaPacienteService(DoencaRepository doencaRepository, DoencaPacienteMapper doencaPacienteMapper, PacienteRepository pacienteRepository,
                                 DoencaPacienteRepository doencaPacienteRepository, MedicacaoMapper medicacaoMapper, TratamentoMapper tratamentoMapper,
                                 SintomaMapper sintomaMapper, AlergiaMapper alergiaMapper, DoencaService doencaService) {
        this.doencaRepository = doencaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doencaPacienteMapper = doencaPacienteMapper;
        this.doencaPacienteRepository = doencaPacienteRepository;
        this.medicacaoMapper = medicacaoMapper;
        this.tratamentoMapper = tratamentoMapper;
        this.sintomaMapper = sintomaMapper;
        this.alergiaMapper = alergiaMapper;
        this.doencaService = doencaService;
    }

    public DetalhesDoencaPacienteDTO cadastrarDoencaPaciente(CadastrarDoencaPaciente dto) {

        Paciente pacienteLocalizado = localizarPaciente(dto.paciente().cpf());
        validarDoencaExistente(dto.doenca().nomeDoenca());
        validarDoencasDuplicadas(pacienteLocalizado, List.of(doencaPacienteMapper.converterDtoEntidadeCadastro(dto)));

        if(pacienteLocalizado.getStatusPaciente().name().equals("INATIVO")){
            throw new PacienteNaoLocalizado("Não é possível cadastrar doenças para um paciente inativo.");
        }

        DoencaPaciente doencaPaciente = doencaPacienteMapper.converterDtoEntidadeCadastro(dto);
        doencaPaciente.setPaciente(pacienteLocalizado);
        doencaPacienteRepository.save(doencaPaciente);

        return doencaPacienteMapper.toDetalhesDTO(doencaPaciente);
    }

    @Transactional
    public DetalhesDoencaPacienteDTO atualizarDoencaPaciente(String cpf, AtualizarDoencaPaciente dto) {

        Paciente paciente = localizarPaciente(cpf);

        DoencaPaciente doencaPaciente = paciente.getDoencas().stream()
                .filter(d -> d.getDoenca().getNomeDoenca()
                        .equalsIgnoreCase(dto.doenca().nomeDoenca()))
                .findFirst()
                .orElseThrow(() ->
                        new NomeDoencaNaoLocalizado("Doença não encontrada para o paciente"));

        doencaPaciente.setCronicidade(dto.tipoCronicidade());
        doencaPaciente.setGravidade(dto.gravidade());
        doencaPaciente.setStatus(dto.status());
        doencaPaciente.setObservacoesMedicas(dto.observacaoMedica());
        doencaPaciente.setDataUltimaAtualizacao(LocalDateTime.now());

        atualizarTratamentos(doencaPaciente, dto.tratamentos(), paciente);
        atualizarSintomas(doencaPaciente, dto.sintomas());
        atualizarMedicacoes(doencaPaciente, dto.medicacoes(), paciente);
        atualizarAlergias(doencaPaciente, dto.alergias());
        atualizarHistoricoFamiliar(doencaPaciente, dto.historicoFamiliar());

        Doenca doenca = doencaService.buscarPorNome(dto.doenca().nomeDoenca());
        doencaPaciente.setDoenca(doenca);

        pacienteRepository.save(paciente);

        return doencaPacienteMapper.toDetalhesDTO(doencaPaciente);
    }


    public DetalhesDoencaPacienteDTO desativarDoenca(String cpf, String nomeDoenca) {
        Paciente paciente = localizarPaciente(cpf);

        DoencaPaciente doencaPaciente = paciente.getDoencas().stream()
                .filter(d -> d.getDoenca().getNomeDoenca()
                        .equalsIgnoreCase(nomeDoenca))
                .findFirst()
                .orElseThrow(() ->
                        new NomeDoencaNaoLocalizado("Doença não encontrada para o paciente"));

        if(doencaPaciente.getCronicidade().name().equals("CRONICA")){
            throw new DoencaDuplicada("Doença crônica não pode ser desativada.");
        }

        doencaPaciente.setAtivo(false);
        doencaPaciente.setDataUltimaAtualizacao(LocalDateTime.now());

        doencaPacienteRepository.save(doencaPaciente);

        return doencaPacienteMapper.toDetalhesDTO(doencaPaciente);
    }

    public DetalhesDoencaPacienteDTO ativarDoenca(String cpf, String nomeDoenca) {
        Paciente paciente = localizarPaciente(cpf);

        DoencaPaciente doencaPaciente = paciente.getDoencas().stream()
                .filter(d -> d.getDoenca().getNomeDoenca()
                        .equalsIgnoreCase(nomeDoenca))
                .findFirst()
                .orElseThrow(() ->
                        new NomeDoencaNaoLocalizado("Doença não encontrada para o paciente"));

        doencaPaciente.setAtivo(true);
        doencaPaciente.setDataUltimaAtualizacao(LocalDateTime.now());

        doencaPacienteRepository.save(doencaPaciente);

        return doencaPacienteMapper.toDetalhesDTO(doencaPaciente);
    }

    public DetalhesDoencaPacienteDTO buscarDoencaPaciente(String cpf, String nomeDoenca) {
        Paciente paciente = localizarPaciente(cpf);

        DoencaPaciente doencaPaciente = paciente.getDoencas().stream()
                .filter(d -> d.getDoenca().getNomeDoenca()
                        .equalsIgnoreCase(nomeDoenca))
                .findFirst()
                .orElseThrow(() ->
                        new NomeDoencaNaoLocalizado("Doença não encontrada para o paciente"));

        return doencaPacienteMapper.toDetalhesDTO(doencaPaciente);
    }

    public List<DetalhesDoencaPacienteDTO> mostrarTodasDoencasPaciente(String cpf) {
        Paciente paciente = localizarPaciente(cpf);

        if (paciente.getDoencas().isEmpty()) {
            throw new NomeDoencaNaoLocalizado("Nenhuma doença encontrada para o paciente.");
        }

        List<DoencaPaciente> doencaPaciente = paciente.getDoencas();

        return doencaPacienteMapper.toDetalhesListDTO(doencaPaciente);
    }

    private Paciente localizarPaciente(String cpf) {
        Optional<Paciente> pacienteLocalizado = pacienteRepository.findByCpf(cpf);
        if (pacienteLocalizado.isEmpty()) {
            throw new PacienteNaoLocalizado("Erro ao localizar paciente pelo CPF informado.");
        }
        return pacienteLocalizado.get();
    }

    private void validarDoencasDuplicadas(Paciente paciente, List<DoencaPaciente> novasDoencas) {

        List<DoencaPaciente> doencasExistentes = paciente.getDoencas();

        for (DoencaPaciente nova : novasDoencas) {
            boolean jaExiste = doencasExistentes.stream()
                    .anyMatch(existente ->
                            existente.getDoenca().getId()
                                    .equals(nova.getDoenca().getId())
                    );
            if (jaExiste) {
                throw new DoencaDuplicada(
                        "Doença já cadastrada para o paciente."
                );
            }
        }
    }

    private void validarDoencaExistente(String nomeDoenca) {
        Optional<Doenca> doencaLocalizada = doencaRepository.findByNomeDoenca(nomeDoenca);
        if(doencaLocalizada.isEmpty()){
            throw new NomeDoencaNaoLocalizado(
                    "Doença informada não existe no sistema."
            );
        }
    }

    private void atualizarMedicacoes(
            DoencaPaciente dp,
            List<MedicacaoDTO> dtos,
            Paciente paciente) {

        List<MedicacaoPaciente> atuais = dp.getMedicacoes();

        for (MedicacaoDTO dto : dtos) {

            if (dto.nomeMedicamento() != null) {
                MedicacaoPaciente existente = atuais.stream()
                        .filter(m -> m.getMedicacao().getNomeMedicamento().equals(dto.nomeMedicamento()))
                        .findFirst()
                        .orElseThrow(() ->
                                new MedicamentoNaoLocalizado("Medicação não encontrada"));

                existente.getMedicacao().setNomeMedicamento(dto.nomeMedicamento());
                existente.setDosagem(dto.dosagem());
                existente.setViaAdministracao(dto.via());
                existente.setFrequencia(dto.frequencia());

            } else {
                MedicacaoPaciente nova = medicacaoMapper.converteParaEntidade(
                        dto, paciente, (Tratamento) dp.getTratamentos()
                );
                atuais.add(nova);
            }
        }

        atuais.removeIf(m ->
                dtos.stream().noneMatch(d ->
                        d.nomeMedicamento() != null && d.nomeMedicamento().equals(m.getMedicacao().getNomeMedicamento()))
        );
    }

    private void atualizarTratamentos(
            DoencaPaciente dp,
            List<TratamentoDTO> dtos,
            Paciente paciente) {

        List<Tratamento> atuais = dp.getTratamentos();

        for (TratamentoDTO dto : dtos) {

            if (dto.nome() != null) {
                Tratamento existente = atuais.stream()
                        .filter(t -> t.getNome().equals(dto.nome()))
                        .findFirst()
                        .orElseThrow(() ->
                                new TratamentoNaoLocalizado("Tratamento não encontrado"));

                existente.setDescricao(dto.descricao());

            } else {
                Tratamento novo = tratamentoMapper.toEntity(dto);
                atuais.add(novo);
            }
        }

        atuais.removeIf(t ->
                dtos.stream().noneMatch(d ->
                        d.nome() != null && d.nome().equals(t.getNome()))
        );
    }

    private void atualizarSintomas(
            DoencaPaciente dp,
            List<SintomaDTO> dtos) {

        List<Sintoma> atuais = dp.getSintomas();

        for (SintomaDTO dto : dtos) {

            if (dto.nome() != null) {
                Sintoma existente = atuais.stream()
                        .filter(s -> s.getNome().equals(dto.nome()))
                        .findFirst()
                        .orElseThrow(() ->
                                new SintomaNaoLocalizado("Sintoma não encontrado"));

                existente.setDescricao(dto.descricao());
                existente.setGravidade(dto.gravidade());

            } else {
                atuais.add(sintomaMapper.toEntity(dto));
            }
        }

        atuais.removeIf(s ->
                dtos.stream().noneMatch(d ->
                        d.nome() != null && d.nome().equals(s.getNome()))
        );
    }

    private void atualizarAlergias(
            DoencaPaciente dp,
            List<AlergiaDTO> dtos) {

        List<Alergia> atuais = dp.getAlergias();

        for (AlergiaDTO dto : dtos) {

            if (dto.nome() != null) {
                Alergia existente = atuais.stream()
                        .filter(a -> a.getNome().equals(dto.nome()))
                        .findFirst()
                        .orElseThrow(() ->
                                new AlergiaNaoLocalizada("Alergia não encontrada"));

                existente.setDescricao(dto.descricao());

            } else {
                atuais.add(alergiaMapper.toEntity(dto));
            }
        }

        atuais.removeIf(a ->
                dtos.stream().noneMatch(d ->
                        d.nome() != null && d.nome().equals(a.getNome()))
        );
    }

    private void atualizarHistoricoFamiliar(
            DoencaPaciente dp,
            List<HistoricoFamiliar> historicos) {

        dp.getHistoricoFamiliar().clear();
        dp.getHistoricoFamiliar().addAll(historicos);
    }

}
