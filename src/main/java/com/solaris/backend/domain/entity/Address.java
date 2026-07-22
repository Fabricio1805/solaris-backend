package com.solaris.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "address")
@Entity(name = "address")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    private String zipCode;
    private String city;
    private String street;
    private String state;
    private String country;
    private String number;
    private String neighborhood;
    private String complement;
}
