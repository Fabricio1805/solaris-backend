package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.WorkingHoursDTO;
import com.solaris.backend.domain.entity.WorkingHours;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
            CompanyMapper.class,
        }
)
public interface WorkingHoursMapper extends BaseMapper<WorkingHoursDTO, WorkingHours> {
}
