package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.AddressDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.AddressMapper;
import com.solaris.backend.application.repository.AddressRepository;
import com.solaris.backend.application.service.AddressService;
import com.solaris.backend.domain.entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * @param dto
     * @return
     */
    @Override
    public AddressDTO save(AddressDTO dto) {
        logger.info("Iniciando cadastro de endereço.");

        Address entity = addressMapper.toEntity(dto);
        entity = addressRepository.save(entity);

        logger.info("Endereço cadastrado com sucesso. ID: {}", entity.getId());

        return addressMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public AddressDTO update(Long id, AddressDTO dto) {
        logger.info("Iniciando atualização do endereço. ID: {}", id);

        Address address = findEntityById(id);

        addressMapper.updateEntityFromDTO(dto, address);

        address = addressRepository.save(address);

        logger.info("Endereço atualizado com sucesso. ID: {}", address.getId());

        return addressMapper.toDTO(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressDTO findById(Long id) {
        logger.info("Buscando endereço por ID: {}", id);

        Address address = findEntityById(id);

        logger.info("Endereço encontrado. ID: {}", address.getId());

        return addressMapper.toDTO(address);
    }

    /**
     * @return
     */
    @Override
    public List<AddressDTO> findAll() {
        logger.info("Listando todos os endereços.");

        List<Address> addresses = addressRepository.findAll();

        logger.info("Total de endereços encontrados: {}", addresses.size());

        return addressMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo endereço. ID: {}", id);

        Address address = findEntityById(id);
        addressRepository.delete(address);

        logger.info("Endereço removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Address findEntityById(Long id) {
        logger.debug("Consultando entidade Address. ID: {}", id);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Endereço não encontrado. ID: {}", id);
                    return new NotFoundException("Endereço não encontrado");
                });

        logger.debug("Entidade Address localizada. ID: {}", address.getId());

        return address;
    }
}
