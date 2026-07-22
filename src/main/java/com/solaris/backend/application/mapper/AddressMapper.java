package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.AddressDTO;
import com.solaris.backend.domain.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<AddressDTO, Address>  {
}
