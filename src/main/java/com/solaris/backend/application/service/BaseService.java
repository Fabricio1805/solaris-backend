package com.solaris.backend.application.service;

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
    List<D> findAll();

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
