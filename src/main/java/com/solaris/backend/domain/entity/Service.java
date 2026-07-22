package com.solaris.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Table(name = "service")
@Entity(name = "service")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Service extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private String photo;
    private Boolean active;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
