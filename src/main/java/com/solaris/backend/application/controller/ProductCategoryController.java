package com.solaris.backend.application.controller;


import com.solaris.backend.application.dto.ProductCategoryDTO;
import com.solaris.backend.application.service.ProductCategoryService;
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
@RequestMapping("/product_categories")
public class ProductCategoryController {
    private static final Logger logger = LogManager.getLogger(ProductCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    @Operation(summary = "Cadastrar categoria produto")
    @PostMapping
    public ResponseEntity<ProductCategoryDTO> save(@RequestBody @Valid ProductCategoryDTO dto) {
        logger.info("Cadastrando categoria produto");
        ProductCategoryDTO response = productCategoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar categoria produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProductCategoryDTO dto) {

        logger.info("Atualizando categoria produto de id {}", id);

        ProductCategoryDTO response = productCategoryService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar categoria produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> findById(@PathVariable Long id) {

        logger.info("Buscando categoria produto de id {}", id);

        ProductCategoryDTO response = productCategoryService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os categorias produtos")
    @GetMapping
    public ResponseEntity<Page<ProductCategoryDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os categorias produtos");

        Page<ProductCategoryDTO> response = productCategoryService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir categoria produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo categoria produto de id {}", id);

        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
