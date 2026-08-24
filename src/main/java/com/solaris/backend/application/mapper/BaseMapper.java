package com.solaris.backend.application.mapper;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<D, E> {
    D toDTO(E entity);

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtos);

    default Page<D> toDTOList(Page<E> entities) {
        return entities.map(this::toDTO);
    }

    void updateEntityFromDTO(D dto, @MappingTarget E entity);
}
