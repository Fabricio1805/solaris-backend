package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.ServiceDTO;
import com.solaris.backend.domain.entity.Service;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
            CompanyMapper.class,
        }
)
public interface ServiceMapper extends BaseMapper<ServiceDTO, Service>  {
}


