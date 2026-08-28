package com.solaris.backend.application.repository;

import com.solaris.backend.domain.entity.Address;
import com.solaris.backend.domain.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Page<ProductCategory> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
