package br.com.uniesp.assistencia.internal.cliente.controller;

import br.com.uniesp.assistencia.internal.cliente.clienteDTO.ClienteDTO;
import br.com.uniesp.assistencia.internal.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {
    
    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listar() {
        log.info("Listando todos os clientes");
        List<ClienteDTO> clientes = clienteService.listar();
        log.debug("Total de clientes encontrados: {}", clientes.size());
        return clientes;
    }

    @GetMapping("/listapaginada")
    public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable) {
        Page<ClienteDTO> dto = clienteService.listaPaginada(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        try {
            ClienteDTO cliente = clienteService.buscarPorId(id);
            log.debug("Cliente encontrado: {}", cliente);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            log.error("Erro ao buscar cliente com ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscaNome")
    public List<ClienteDTO> buscaNome(String nome) {
        log.info("Listando todos os clientes com nome ou parte do nome igual a: {}", nome);
        try {
            List<ClienteDTO> clientes = clienteService.buscaPorNome(nome);
            log.debug("Total de clientes com nome ou parte do nome igual a: {} encontrados: {}", nome, clientes.size());
            return clientes;
        } catch (Exception e) {
            log.error("Erro ao buscar clientes com nome ou parte do nome igual a: {}: {}", nome, e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO ClienteDTO) {
        log.info("Recebida requisição para criar novo cliente: {}", ClienteDTO.getId());
        try {
            ClienteDTO clienteSalvo = clienteService.salvar(ClienteDTO);
            log.info("Cliente salvo com sucesso. ID: {}, Nome: {}", clienteSalvo.getId(), clienteSalvo.getNome());

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(clienteSalvo.getId())
                    .toUri();
            log.debug("URI de localização do novo cliente: {}", location);

            return ResponseEntity.created(location).body(clienteSalvo);
        } catch (Exception e) {
            log.error("Erro ao salvar cliente: {}", e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO ClienteDTO) {
        log.info("Atualizando cliente ID: {}, {}", id, ClienteDTO);
        try {
            ClienteDTO clienteAtualizado = clienteService.atualizar(id, ClienteDTO);
            log.debug("Cliente ID: {} atualizado com sucesso", id);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (Exception e) {
            log.error("Erro ao atualizar cliente ID: {}, {}", id, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("Excluindo cliente ID: {}", id);
        try {
            clienteService.excluir(id);
            log.debug("Cliente ID: {} excluído com sucesso!", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir cliente ID: {}, {}", id, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }
    
}
