package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.ProductDTO;
import com.solaris.backend.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductDTO, Product> {
}
