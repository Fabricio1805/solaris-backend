package com.solaris.backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDTO( Long id,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt,
                          Long createdId,
                          Long updatedId,

                         @NotBlank(message = "Nome é obrigatório")
                         @Schema(description = "Nome do produto", example = "Notebook")
                         String name,

                         @NotBlank(message = "Descrição é obrigatória")
                         String description,

                         @NotNull(message = "Preço é obrigatório")
                         @Positive(message = "Preço deve ser maior que zero")
                         @Schema(description = "Preço", example = "3500.00")
                         BigDecimal price,

                         @NotNull(message = "Status é obrigatório")
                         Boolean active,

                         @NotBlank(message = "Imagem é obrigatória")
                         String image) {
}
