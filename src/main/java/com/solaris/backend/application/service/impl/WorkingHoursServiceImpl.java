package com.solaris.backend.application.service.impl;


import com.solaris.backend.application.dto.WorkingHoursDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.WorkingHoursMapper;
import com.solaris.backend.application.repository.WorkingHoursRepository;
import com.solaris.backend.application.service.WorkingHoursService;
import com.solaris.backend.domain.entity.WorkingHours;
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
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private static final Logger logger = LogManager.getLogger(WorkingHoursServiceImpl.class);

    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    @Autowired
    private WorkingHoursMapper workingHoursMapper;

    /**
     * @param dto
     * @return
     */
    @Override
    public WorkingHoursDTO save(WorkingHoursDTO dto) {
        logger.info("Iniciando cadastro de hora de trabalho.");

        WorkingHours entity = workingHoursMapper.toEntity(dto);
        entity = workingHoursRepository.save(entity);

        logger.info("Endereço cadastrado com sucesso. ID: {}", entity.getId());

        return workingHoursMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public WorkingHoursDTO update(Long id, WorkingHoursDTO dto) {
        logger.info("Iniciando atualização do hora de trabalho. ID: {}", id);

        WorkingHours address = findEntityById(id);

        workingHoursMapper.updateEntityFromDTO(dto, address);

        address = workingHoursRepository.save(address);

        logger.info("Endereço atualizado com sucesso. ID: {}", address.getId());

        return workingHoursMapper.toDTO(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public WorkingHoursDTO findById(Long id) {
        logger.info("Buscando hora de trabalho por ID: {}", id);

        WorkingHours address = findEntityById(id);

        logger.info("Endereço encontrado. ID: {}", address.getId());

        return workingHoursMapper.toDTO(address);
    }

    /**
     * @return
     */
    @Override
    public Page<WorkingHoursDTO> findAll(Pageable pageable) {
        logger.info("Listando todos os horas de trabalho.");

        Page<WorkingHours> addresses = workingHoursRepository.findAll(pageable);

        logger.info("Total de horas de trabalho encontrados: {}", addresses.getSize());

        return workingHoursMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo hora de trabalho. ID: {}", id);

        WorkingHours address = findEntityById(id);
        workingHoursRepository.delete(address);

        logger.info("Endereço removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public WorkingHours findEntityById(Long id) {
        logger.debug("Consultando entidade WorkingHours. ID: {}", id);

        WorkingHours address = workingHoursRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Endereço não encontrado. ID: {}", id);
                    return new NotFoundException("Endereço não encontrado");
                });

        logger.debug("Entidade WorkingHours localizada. ID: {}", address.getId());

        return address;
    }
}
