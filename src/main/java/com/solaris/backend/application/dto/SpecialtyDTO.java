package com.solaris.backend.application.dto;

import java.time.LocalDateTime;

public record SpecialtyDTO(Long id,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           Long createdId,
                           Long updatedId,
                           String name) {
}
