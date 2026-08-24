package com.solaris.backend.application.controller;

import com.solaris.backend.application.dto.ProductDTO;
import com.solaris.backend.application.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Operation(summary = "Cadastrar produto")
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductDTO dto) {
        logger.info("Cadastrando produto");
        ProductDTO response = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProductDTO dto) {

        logger.info("Atualizando produto de id {}", id);

        ProductDTO response = productService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

        logger.info("Buscando produto de id {}", id);

        ProductDTO response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {

        logger.info("Listando todos os produtos");

        Page<ProductDTO> response = productService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        logger.info("Excluindo produto de id {}", id);

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
