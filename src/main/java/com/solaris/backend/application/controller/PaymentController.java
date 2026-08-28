package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.PaymentDTO;
import com.solaris.backend.application.dto.PaymentDTO;
import com.solaris.backend.application.service.PaymentService;
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
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger logger = LogManager.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Cadastrar pagamento")
    @PostMapping
    public ResponseEntity<PaymentDTO> save(@RequestBody @Valid PaymentDTO dto) {
        logger.info("Cadastrando pagamento");
        PaymentDTO response = paymentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar pagamento")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid PaymentDTO dto) {

        logger.info("Atualizando pagamento de id {}", id);

        PaymentDTO response = paymentService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {

        logger.info("Buscando pagamento de id {}", id);

        PaymentDTO response = paymentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os pagamentos")
    @GetMapping
    public ResponseEntity<Page<PaymentDTO>> findAll(@RequestParam(required = false) String name, Pageable pageable) {

        logger.info("Listando todos os pagamentos");

        Page<PaymentDTO> response = paymentService.findAll(name,pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo pagamento de id {}", id);

        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
