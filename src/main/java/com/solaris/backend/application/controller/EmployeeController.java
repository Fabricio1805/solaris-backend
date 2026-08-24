package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.EmployeeDTO;
import com.solaris.backend.application.dto.EmployeeDTO;
import com.solaris.backend.application.service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Cadastrar empregado")
    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody @Valid EmployeeDTO dto) {
        logger.info("Cadastrando empregado");
        EmployeeDTO response = employeeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar empregado")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeDTO dto) {

        logger.info("Atualizando empregado de id {}", id);

        EmployeeDTO response = employeeService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar empregado por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {

        logger.info("Buscando empregado de id {}", id);

        EmployeeDTO response = employeeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os empregados")
    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os empregados");

        Page<EmployeeDTO> response = employeeService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir empregado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo empregado de id {}", id);

        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
