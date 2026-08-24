package com.solaris.backend.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<D, E ,ID> {

    /**
     * @param dto
     * @return
     */
    D save(D dto);

    /**
     * @param id
     * @param dto
     * @return
     */
    D update(ID id, D dto);

    /**
     * @param id
     * @return
     */
    D findById(ID id);

    /**
     * @return
     */
    Page<D> findAll(Pageable pageable);

    /**
     * @param id
     */
    void delete(ID id);


    /**
     * @param id
     * @return
     */
    E findEntityById(ID id);
}
