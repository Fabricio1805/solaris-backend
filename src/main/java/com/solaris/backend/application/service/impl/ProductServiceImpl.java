package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.ProductDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.ProductMapper;
import com.solaris.backend.application.repository.ProductRepository;
import com.solaris.backend.application.service.ProductService;
import com.solaris.backend.domain.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    /**
     * @param dto
     * @return
     */
    @Override
    public ProductDTO save(ProductDTO dto) {
        logger.info("Iniciando cadastro de produto.");

        Product entity = productMapper.toEntity(dto);
        entity = productRepository.save(entity);

        logger.info("Produto cadastrado com sucesso. ID: {}", entity.getId());

        return productMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        logger.info("Iniciando atualização do produto. ID: {}", id);

        Product product = findEntityById(id);

        productMapper.updateEntityFromDTO(dto, product);

        product = productRepository.save(product);

        logger.info("Produto atualizado com sucesso. ID: {}", product.getId());

        return productMapper.toDTO(product);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductDTO findById(Long id) {
        logger.info("Buscando produto por ID: {}", id);

        Product product = findEntityById(id);

        logger.info("Produto encontrado. ID: {}", product.getId());

        return productMapper.toDTO(product);
    }

    /**
     * @return
     */
    @Override
    public List<ProductDTO> findAll() {
        logger.info("Listando todos os produtos.");

        List<Product> addresses = productRepository.findAll();

        logger.info("Total de produtos encontrados: {}", addresses.size());

        return productMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo produto. ID: {}", id);

        Product product = findEntityById(id);
        productRepository.delete(product);

        logger.info("Produto removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Product findEntityById(Long id) {
        logger.debug("Consultando entidade Product. ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Produto não encontrado. ID: {}", id);
                    return new NotFoundException("Produto não encontrado");
                });

        logger.debug("Entidade Product localizada. ID: {}", product.getId());

        return product;
    }
}
