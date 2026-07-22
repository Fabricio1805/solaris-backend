package com.solaris.backend.application.dto;

import com.solaris.backend.domain.enums.PaymentMethod;
import com.solaris.backend.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long createdId,
        Long updatedId,
        AppointmentDTO appointment,
        BigDecimal amount,
        PaymentStatus status,
        PaymentMethod paymentMethod,
        LocalDateTime paidAt) {
}
