package com.solaris.backend.domain.entity;

import com.solaris.backend.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "appointment")
@Entity(name = "appointment")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String notes;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Date date;

    private BigDecimal price;

    private BigDecimal commission;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
