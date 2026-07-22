package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.ClientDTO;
import com.solaris.backend.domain.entity.Client;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                CompanyMapper.class,
                AddressMapper.class,
        }
)
public interface ClientMapper extends BaseMapper<ClientDTO, Client>{
}
