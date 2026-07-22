package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.ProductCategoryDTO;
import com.solaris.backend.domain.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDTO, ProductCategory> {
}
