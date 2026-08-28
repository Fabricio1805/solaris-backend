package com.solaris.backend.application.repository;

import com.solaris.backend.domain.entity.Address;
import com.solaris.backend.domain.entity.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Page<Specialty> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
