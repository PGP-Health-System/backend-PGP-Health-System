package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.EnderecoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.EnderecoResponseDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoRepository getEnderecoRepository() {
        return enderecoRepository;
    }

    public EnderecoDTO salvaEndereco(EnderecoResponseDTO enderecoResponseDTO) {

        if (enderecoRepository.existsByCepAndNumero(enderecoResponseDTO.cep()
                , enderecoResponseDTO.numero())) {

            log.warn("Tentativa de criar endereço duplicado: CEP={} Número={}",
                    enderecoResponseDTO.cep(), enderecoResponseDTO.numero());

            throw new IllegalArgumentException("Endereço não pode ser criado pois já registrado");

        }

        log.info("Carregando endereço... ");
        Endereco novoEndereco = new Endereco();
        novoEndereco.setLogradouro(enderecoResponseDTO.logradouro());
        novoEndereco.setBairro(enderecoResponseDTO.bairro());
        novoEndereco.setCidade(enderecoResponseDTO.cidade());
        novoEndereco.setEstado(enderecoResponseDTO.estado());
        novoEndereco.setCep(enderecoResponseDTO.cep());
        novoEndereco.setComplemento(enderecoResponseDTO.complemento());
        novoEndereco.setNumero(enderecoResponseDTO.numero());

        log.info("Carregado com sucesso! LOGRADOURO");


        Endereco endereco = enderecoRepository.save(novoEndereco);
        log.info("Endereço salvo! logradouro={} bairro={} cidade={} estado={} cep={} numero={} complemento={} ",
                enderecoResponseDTO.logradouro(), enderecoResponseDTO.bairro(), enderecoResponseDTO.cidade(),
                enderecoResponseDTO.estado(), enderecoResponseDTO.cep(), enderecoResponseDTO.numero(),
                enderecoResponseDTO.complemento());

        return new EnderecoDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }


}
