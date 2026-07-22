package com.solaris.backend.application.dto;

import java.time.LocalDateTime;

public record EmployeeDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long createdId,
        Long updatedId,
        CompanyDTO company,
        SpecialtyDTO specialty,
        AddressDTO address,
        Boolean active,
        String colorCalendar) {
}
