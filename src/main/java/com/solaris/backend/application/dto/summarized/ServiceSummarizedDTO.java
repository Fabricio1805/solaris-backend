package com.solaris.backend.application.dto.summarized;

import java.math.BigDecimal;

public record ServiceSummarizedDTO(Long id,
                                   String name,
                                   String description,
                                   BigDecimal price) {
}
