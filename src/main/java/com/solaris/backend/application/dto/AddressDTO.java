package com.solaris.backend.application.dto;

import java.time.LocalDateTime;

public record AddressDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long createdId,
        Long updatedId,
        String number,
        String street,
        String city,
        String state,
        String zipCode,
        String country,
        String neighborhood,
        String complement) {
}
