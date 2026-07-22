package com.solaris.backend.application.dto.error;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO (
        LocalDateTime timestamp,
        Integer status,
        String error,
        List<ValidationErrorDTO> messages,
        String message,
        String path
){
}
