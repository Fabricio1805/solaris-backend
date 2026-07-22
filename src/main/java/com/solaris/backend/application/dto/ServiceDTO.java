package com.solaris.backend.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long createdId,
        Long updatedId,
         String name,
         String description,
         BigDecimal price,
         String photo,
         Boolean active,
         Integer duration,
         CompanyDTO company
) {
}
