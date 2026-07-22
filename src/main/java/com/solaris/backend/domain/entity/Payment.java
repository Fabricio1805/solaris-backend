package com.solaris.backend.domain.entity;

import com.solaris.backend.domain.enums.PaymentMethod;
import com.solaris.backend.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "payment")
@Entity(name = "payment")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDateTime paidAt;
}
