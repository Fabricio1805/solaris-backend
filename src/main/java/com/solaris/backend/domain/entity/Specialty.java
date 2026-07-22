package com.solaris.backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "specialty")
@Entity(name = "specialty")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Specialty extends BaseEntity {
    private String name;
}

/*
Exemplos:
Dentista
        Cabeleireiro
Barbeiro
        Esteticista
Manicure*/