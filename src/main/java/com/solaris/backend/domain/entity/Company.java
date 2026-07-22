package com.solaris.backend.domain.entity;

import com.solaris.backend.domain.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "company")
@Entity(name = "company")
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends  BaseEntity {
    private String name;
    private String corporateName;
    private String email;
    private String phone;
    private String document;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
