package com.solaris.backend.application.dto;

import java.time.LocalDateTime;

public record SpecialtyDTO(Long id,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           String createdId,
                           String updatedId,
                           String name) {
}
