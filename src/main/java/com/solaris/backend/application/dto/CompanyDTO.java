package com.solaris.backend.application.dto;

import com.solaris.backend.domain.enums.DocumentType;

import java.time.LocalDateTime;

public record CompanyDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdId,
        String updatedId,
         String name,
         String corporateName,
         String email,
         String phone,
         String document,
         DocumentType documentType,
         Boolean active,
         AddressDTO address
) {
}
