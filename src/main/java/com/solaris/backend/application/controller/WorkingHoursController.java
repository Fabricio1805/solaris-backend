package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.WorkingHoursDTO;
import com.solaris.backend.application.service.WorkingHoursService;
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
@RequestMapping("/working_hours")
public class WorkingHoursController {
    private static final Logger logger = LogManager.getLogger(WorkingHoursController.class);

    @Autowired
    private WorkingHoursService workingHoursService;


    @Operation(summary = "Cadastrar hora de trabalho")
    @PostMapping
    public ResponseEntity<WorkingHoursDTO> save(@RequestBody @Valid WorkingHoursDTO dto) {
        logger.info("Cadastrando hora de trabalho");
        WorkingHoursDTO response = workingHoursService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar hora de trabalho")
    @PutMapping("/{id}")
    public ResponseEntity<WorkingHoursDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid WorkingHoursDTO dto) {

        logger.info("Atualizando hora de trabalho de id {}", id);

        WorkingHoursDTO response = workingHoursService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar hora de trabalho por ID")
    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursDTO> findById(@PathVariable Long id) {

        logger.info("Buscando hora de trabalho de id {}", id);

        WorkingHoursDTO response = workingHoursService.findById(id);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Listar todos os horas de trabalho")
    @GetMapping
    public ResponseEntity<Page<WorkingHoursDTO>> findAll(@RequestParam(required = false) String name, Pageable pageable) {

        logger.info("Listando todos os horas de trabalho");

        Page<WorkingHoursDTO> response = workingHoursService.findAll(name, pageable);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Excluir hora de trabalho")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo hora de trabalho de id {}", id);

        workingHoursService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
