package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.ProductCategoryDTO;
import com.solaris.backend.application.dto.ProductCategoryDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.ProductCategoryMapper;
import com.solaris.backend.application.repository.ProductCategoryRepository;
import com.solaris.backend.application.service.ProductCategoryService;
import com.solaris.backend.domain.entity.ProductCategory;
import com.solaris.backend.domain.entity.ProductCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private static final Logger logger = LogManager.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    
    /**
     * @param dto
     * @return
     */
    @Override
    public ProductCategoryDTO save(ProductCategoryDTO dto) {
        logger.info("Iniciando cadastro de produto categoria.");

        ProductCategory entity = productCategoryMapper.toEntity(dto);
        entity = productCategoryRepository.save(entity);

        logger.info("Produto categoria cadastrado com sucesso. ID: {}", entity.getId());

        return productCategoryMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public ProductCategoryDTO update(Long id, ProductCategoryDTO dto) {
        logger.info("Iniciando atualização do produto categoria. ID: {}", id);

        ProductCategory address = findEntityById(id);

        productCategoryMapper.updateEntityFromDTO(dto, address);

        address = productCategoryRepository.save(address);

        logger.info("Produto categoria atualizado com sucesso. ID: {}", address.getId());

        return productCategoryMapper.toDTO(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductCategoryDTO findById(Long id) {
        logger.info("Buscando produto categoria por ID: {}", id);

        ProductCategory address = findEntityById(id);

        logger.info("Produto categoria encontrado. ID: {}", address.getId());

        return productCategoryMapper.toDTO(address);
    }

    /**
     * @return
     */
    @Override
    public Page<ProductCategoryDTO> findAll(String name, Pageable pageable) {
        logger.info("Listando todos os Produto categorias.");

        Page<ProductCategory> productCategories;

        if (name == null || name.isBlank()) {
            productCategories = productCategoryRepository.findAll(pageable);
        } else {
            productCategories = productCategoryRepository.findByNameContainingIgnoreCase(
                    name,
                    pageable
            );
        }
        logger.info("Total de Produto categorias encontrados: {}", productCategories.getSize());

        return productCategoryMapper.toDTOList(productCategories);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo produto categoria. ID: {}", id);

        ProductCategory address = findEntityById(id);
        productCategoryRepository.delete(address);

        logger.info("Produto categoria removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductCategory findEntityById(Long id) {
        logger.debug("Consultando entidade ProductCategory. ID: {}", id);

        ProductCategory address = productCategoryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Produto categoria não encontrado. ID: {}", id);
                    return new NotFoundException("Produto categoria não encontrado");
                });

        logger.debug("Entidade ProductCategory localizada. ID: {}", address.getId());

        return address;
    }
}
