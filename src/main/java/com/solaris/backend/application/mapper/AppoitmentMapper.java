package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.AppointmentDTO;
import com.solaris.backend.domain.entity.Appointment;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                ClientMapper.class,
                CompanyMapper.class,
                ServiceMapper.class,
                EmployeeMapper.class,
                SpecialtyMapper.class
        }
)
public interface AppoitmentMapper extends BaseMapper<AppointmentDTO, Appointment>  {
}
