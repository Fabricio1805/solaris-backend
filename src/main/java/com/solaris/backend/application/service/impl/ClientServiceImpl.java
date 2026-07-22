package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.ClientDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.ClientMapper;
import com.solaris.backend.application.repository.ClientRepository;
import com.solaris.backend.application.service.ClientService;
import com.solaris.backend.domain.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;


    /**
     * @param dto
     * @return
     */
    @Override
    public ClientDTO save(ClientDTO dto) {
        Client entity = clientMapper.toEntity(dto);

        entity = clientRepository.save(entity);

        return clientMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = findEntityById(id);
        clientMapper.updateEntityFromDTO(dto, client);
        client =  clientRepository.save(client);

        return clientMapper.toDTO(client);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ClientDTO findById(Long id) {
        Client client = findEntityById(id);
        return clientMapper.toDTO(client);
    }

    /**
     * @return
     */
    @Override
    public List<ClientDTO> findAll() {
        return clientMapper.toDTOList(clientRepository.findAll());
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        Client client = findEntityById(id);
        clientRepository.delete(client);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Client findEntityById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }
}
