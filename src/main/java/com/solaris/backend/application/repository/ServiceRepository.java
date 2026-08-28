package com.solaris.backend.application.repository;

import com.solaris.backend.domain.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
