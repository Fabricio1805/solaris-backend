package com.solaris.backend.application.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record WorkingHoursDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdId,
        String updatedId,
        CompanyDTO company,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {
}
