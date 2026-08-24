package com.solaris.backend.application.controller;


import com.solaris.backend.application.dto.SpecialtyDTO;
import com.solaris.backend.application.service.SpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    private static final Logger logger = LogManager.getLogger(SpecialtyController.class);

    @Autowired
    private SpecialtyService specialtyService;

    @Operation(summary = "Cadastrar especialidade")
    @PostMapping
    public ResponseEntity<SpecialtyDTO> save(@RequestBody @Valid SpecialtyDTO dto) {
        logger.info("Cadastrando especialidade");
        SpecialtyDTO response = specialtyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar especialidade")
    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid SpecialtyDTO dto) {

        logger.info("Atualizando especialidade de id {}", id);

        SpecialtyDTO response = specialtyService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar especialidade por ID")
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable Long id) {

        logger.info("Buscando especialidade de id {}", id);

        SpecialtyDTO response = specialtyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os especialidades")
    @GetMapping
    public ResponseEntity<Page<SpecialtyDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os especialidades");

        Page<SpecialtyDTO> response = specialtyService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir especialidade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo especialidade de id {}", id);

        specialtyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
