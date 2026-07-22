package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.ServiceDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.ServiceMapper;
import com.solaris.backend.application.repository.ServiceRepository;
import com.solaris.backend.application.service.ServiceService;
import com.solaris.backend.domain.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LogManager.getLogger(ServiceServiceImpl.class);

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;


    /**
     * @param dto
     * @return
     */
    @Override
    public ServiceDTO save(ServiceDTO dto) {
        logger.info("Iniciando cadastro de serviço.");

        Service entity = serviceMapper.toEntity(dto);
        entity = serviceRepository.save(entity);

        logger.info("Serviço cadastrado com sucesso. ID: {}", entity.getId());

        return serviceMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public ServiceDTO update(Long id, ServiceDTO dto) {
        logger.info("Iniciando atualização do serviço. ID: {}", id);

        Service address = findEntityById(id);

        serviceMapper.updateEntityFromDTO(dto, address);

        address = serviceRepository.save(address);

        logger.info("Serviço atualizado com sucesso. ID: {}", address.getId());

        return serviceMapper.toDTO(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ServiceDTO findById(Long id) {
        logger.info("Buscando serviço por ID: {}", id);

        Service address = findEntityById(id);

        logger.info("Serviço encontrado. ID: {}", address.getId());

        return serviceMapper.toDTO(address);
    }

    /**
     * @return
     */
    @Override
    public List<ServiceDTO> findAll() {
        logger.info("Listando todos os serviços.");

        List<Service> addresses = serviceRepository.findAll();

        logger.info("Total de serviços encontrados: {}", addresses.size());

        return serviceMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo serviço. ID: {}", id);

        Service address = findEntityById(id);
        serviceRepository.delete(address);

        logger.info("Serviço removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Service findEntityById(Long id) {
        logger.debug("Consultando entidade Service. ID: {}", id);

        Service address = serviceRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Serviço não encontrado. ID: {}", id);
                    return new NotFoundException("Serviço não encontrado");
                });

        logger.debug("Entidade Service localizada. ID: {}", address.getId());

        return address;
    }
}
