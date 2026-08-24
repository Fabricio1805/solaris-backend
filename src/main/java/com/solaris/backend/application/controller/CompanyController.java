package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.CompanyDTO;
import com.solaris.backend.application.dto.CompanyDTO;
import com.solaris.backend.application.service.CompanyService;
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
@RequestMapping("/companies")
public class CompanyController {
    private static final Logger logger = LogManager.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Cadastrar empresa ")
    @PostMapping
    public ResponseEntity<CompanyDTO> save(@RequestBody @Valid CompanyDTO dto) {
        logger.info("Cadastrando empresa ");
        CompanyDTO response = companyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar empresa ")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid CompanyDTO dto) {

        logger.info("Atualizando empresa  de id {}", id);

        CompanyDTO response = companyService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar empresa  por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id) {

        logger.info("Buscando empresa  de id {}", id);

        CompanyDTO response = companyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os empresas")
    @GetMapping
    public ResponseEntity<Page<CompanyDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os empresas");

        Page<CompanyDTO> response = companyService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir empresa ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo empresa  de id {}", id);

        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
