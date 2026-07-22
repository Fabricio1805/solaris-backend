package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.SpecialtyDTO;
import com.solaris.backend.domain.entity.Specialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper extends BaseMapper<SpecialtyDTO, Specialty> {
}
