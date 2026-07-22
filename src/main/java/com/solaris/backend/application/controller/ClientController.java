package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.ClientDTO;
import com.solaris.backend.application.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Cadastrar cliente")
    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody @Valid ClientDTO dto) {
        logger.info("Cadastrando cliente");
        ClientDTO response = clientService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ClientDTO dto) {

        logger.info("Atualizando cliente de id {}", id);

        ClientDTO response = clientService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {

        logger.info("Buscando cliente de id {}", id);

        ClientDTO response = clientService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {

        logger.info("Listando todos os clientes");

        List<ClientDTO> response = clientService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo cliente de id {}", id);

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
