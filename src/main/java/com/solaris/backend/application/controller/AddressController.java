package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.AddressDTO;
import com.solaris.backend.application.dto.ProductDTO;
import com.solaris.backend.application.service.AddressService;
import com.solaris.backend.application.service.impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LogManager.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;


    @Operation(summary = "Cadastrar endereço")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> save(@RequestBody @Valid AddressDTO dto) {
        logger.info("Cadastrando endereço");
        AddressDTO response = addressService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AddressDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid AddressDTO dto) {

        logger.info("Atualizando endereço de id {}", id);

        AddressDTO response = addressService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar endereço por ID")
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {

        logger.info("Buscando endereço de id {}", id);

        AddressDTO response = addressService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os endereços")
    @GetMapping
    public ResponseEntity<Page<AddressDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os endereços");

        Page<AddressDTO> response = addressService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo endereço de id {}", id);

        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
