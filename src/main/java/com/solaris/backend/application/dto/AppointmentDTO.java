package com.solaris.backend.application.dto;

import com.solaris.backend.domain.enums.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdId,
        String updatedId,
        ClientDTO client,
        CompanyDTO company,
        ServiceDTO service, 
        EmployeeDTO employee,
        String notes,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal price,
        BigDecimal commission,
        AppointmentStatus status) {
}
