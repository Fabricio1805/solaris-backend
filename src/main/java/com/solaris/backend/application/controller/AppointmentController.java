package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.AppointmentDTO;
import com.solaris.backend.application.service.AppointmentService;
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
@RequestMapping("/appointments")
public class AppointmentController {

    private static final Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;


    @Operation(summary = "Cadastrar agendamento")
    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody @Valid AppointmentDTO dto) {
        logger.info("Cadastrando agendamento");
        AppointmentDTO response = appointmentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar agendamento")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid AppointmentDTO dto) {

        logger.info("Atualizando agendamento de id {}", id);

        AppointmentDTO response = appointmentService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar agendamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) {

        logger.info("Buscando agendamento de id {}", id);

        AppointmentDTO response = appointmentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {

        logger.info("Listando todos os agendamentos");

        List<AppointmentDTO> response = appointmentService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo agendamento de id {}", id);

        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
