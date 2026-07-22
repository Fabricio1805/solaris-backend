package com.solaris.backend.application.dto.error;

public record ValidationErrorDTO(String field,
                                 String message) {
}
