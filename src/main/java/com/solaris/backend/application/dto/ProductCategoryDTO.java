package com.solaris.backend.application.dto;

import java.time.LocalDateTime;

public record ProductCategoryDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdId,
        String updatedId,
        String name
){
}
