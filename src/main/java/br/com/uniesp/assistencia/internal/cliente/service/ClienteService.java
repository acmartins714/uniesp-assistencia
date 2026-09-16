package br.com.uniesp.assistencia.internal.cliente.service;

import br.com.uniesp.assistencia.internal.cliente.clienteDTO.ClienteDTO;
import br.com.uniesp.assistencia.internal.cliente.entity.ClienteEntity;
import br.com.uniesp.assistencia.internal.cliente.mapper.ClienteMapper;
import br.com.uniesp.assistencia.internal.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteDTO> listar() {
        log.info("Buscando todas os clientes cadastradas");
        try {
            List<ClienteEntity> clientes = clienteRepository.findAll();
            List<ClienteDTO> clienteDTOS = clientes.stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
            log.debug("Total de clientes encontrados: {}", clienteDTOS.size());
            return clienteDTOS;
        } catch (Exception e) {
            log.error("Falha ao buscar clientes: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ClienteDTO> buscaPorNome(String nome) {
        log.info("Buscando clientes por nome ou parte do nome: {}", nome);
        try {
            List<ClienteEntity> clientes = clienteRepository.buscarPorNome((nome));
            List<ClienteDTO> clienteDTOS = clientes.stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
            log.debug("Total de clientes com nome: {} encontradas: {}", nome, clienteDTOS.size());
            return clienteDTOS;
        } catch (Exception e) {
            log.error("Falha ao buscar clientes por nome: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Page<ClienteDTO> listaPaginada(Pageable pageable) {
        Page<ClienteEntity> result = clienteRepository.findAll(pageable);
        return result.map(clienteMapper::toDTO);
    }

    public ClienteDTO buscarPorId(Long id) {
        log.info("Buscando cliente pelo ID: {}", id);
        ClienteEntity cliente = clienteRepository.findById(id)
                .map(clienteEncontrado -> {
                    log.debug("Cliente encontrado: ID={}, ID Nome={}", clienteEncontrado.getId(), clienteEncontrado.getNome());
                    return clienteEncontrado;
                })
                .orElseThrow(() -> {
                    String mensagem = String.format("Cliente não encontrado com o ID: %d", id);
                    log.warn(mensagem);
                    return new RuntimeException(mensagem);
                });
        return clienteMapper.toDTO(cliente);
    }

    @Transactional
    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        log.info("Atualizando cliente ID: {}", id);
        ClienteEntity clienteAtualizado = clienteRepository.findById(id)
                .map(clienteExistente -> {
                    log.debug("Dados atuais do cliente: {}", clienteExistente);
                    log.debug("Novos dados: {}", clienteDTO);
                    clienteDTO.setId(id);
                    ClienteEntity clienteParaAtualizar = clienteMapper.toEntity(clienteDTO);
                    ClienteEntity clienteSalvo = clienteRepository.save(clienteParaAtualizar);
                    log.info("Cliente ID: {} atualizado com sucesso. Novo ID de Cliente: {}",
                            id, clienteSalvo.getId());
                    return clienteSalvo;
                })
                .orElseThrow(() -> {
                    String mensagem = String.format("Falha ao atualizar: cliente não encontrado com o ID: %d", id);
                    log.warn(mensagem);
                    return new RuntimeException(mensagem);
                });
        return clienteMapper.toDTO(clienteAtualizado);
    }

    @Transactional
    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        log.info("Salvando novo cliente ID: {}, Nome:{}", clienteDTO.getId(), clienteDTO.getNome());
        try {
            ClienteEntity cliente = clienteMapper.toEntity(clienteDTO);
            ClienteEntity clienteSalvo = clienteRepository.save(cliente);
            log.info("Cliente salvo com sucesso. ID: {}, Nome: {}", clienteSalvo.getId(), clienteSalvo.getNome());
            return clienteMapper.toDTO(clienteSalvo);
        } catch (Exception e) {
            log.error("Falha ao salvar cliente ID: {}, Mensagem: {}", clienteDTO.getId(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluir(Long id) {
        log.info("Excluindo cliente ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            String mensagem = String.format("Falha ao excluir: Cliente ID: %d, não encontrado!", id);
            log.warn(mensagem);
            throw new RuntimeException(mensagem);
        }
        try {
            clienteRepository.deleteById(id);
            log.info("Cliente ID: {} excluído com sucesso", id);
        } catch (Exception e) {
            log.error("Erro ao excluir cliente ID: {} - {}", id, e.getMessage(), e);
            throw e;
        }
    }


}
