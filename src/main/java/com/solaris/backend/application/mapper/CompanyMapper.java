package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.CompanyDTO;
import com.solaris.backend.domain.entity.Company;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                AddressMapper.class,
        }
)
public interface CompanyMapper extends BaseMapper<CompanyDTO, Company> {
}
