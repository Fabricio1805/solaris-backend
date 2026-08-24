package com.solaris.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Table(name = "product")
@Entity(name = "product")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;

    private String description;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    private Boolean active;

    private String image;
}
