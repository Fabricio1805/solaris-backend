package com.solaris.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "employee")
@Entity(name = "employee")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty  specialty;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean active;

    private String colorCalendar;
}
