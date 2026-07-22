package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.ServiceDTO;
import com.solaris.backend.application.service.ServiceService;
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
@RequestMapping("/services")
public class ServiceController {

    private static final Logger logger = LogManager.getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;

    @Operation(summary = "Cadastrar serviço")
    @PostMapping
    public ResponseEntity<ServiceDTO> save(@RequestBody @Valid ServiceDTO dto) {
        logger.info("Cadastrando serviço");
        ServiceDTO response = serviceService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar serviço")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ServiceDTO dto) {

        logger.info("Atualizando serviço de id {}", id);

        ServiceDTO response = serviceService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar serviço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> findById(@PathVariable Long id) {

        logger.info("Buscando serviço de id {}", id);

        ServiceDTO response = serviceService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os serviços")
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> findAll() {

        logger.info("Listando todos os serviços");

        List<ServiceDTO> response = serviceService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir serviço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo serviço de id {}", id);

        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
