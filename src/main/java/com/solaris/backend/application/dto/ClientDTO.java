package com.solaris.backend.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdId,
        String updatedId,
         String name,
         String email,
         String phone,
         LocalDate birthday,
         CompanyDTO company,
         AddressDTO address) {
}
