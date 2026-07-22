package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.EmployeeDTO;
import com.solaris.backend.domain.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses =  {
                SpecialtyMapper.class,
                AddressMapper.class,
                CompanyMapper.class
        }
)
public interface EmployeeMapper extends BaseMapper<EmployeeDTO, Employee> {
}
