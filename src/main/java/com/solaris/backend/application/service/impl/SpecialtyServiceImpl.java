package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.SpecialtyDTO;
import com.solaris.backend.application.dto.SpecialtyDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.SpecialtyMapper;
import com.solaris.backend.application.repository.SpecialtyRepository;
import com.solaris.backend.application.service.SpecialtyService;
import com.solaris.backend.domain.entity.Specialty;
import com.solaris.backend.domain.entity.Specialty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {
    private static final Logger logger = LogManager.getLogger(SpecialtyServiceImpl.class);

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private SpecialtyMapper specialtyMapper;

    /**
     * @param dto
     * @return
     */
    @Override
    public SpecialtyDTO save(SpecialtyDTO dto) {
        logger.info("Iniciando cadastro de especialidade.");

        Specialty entity = specialtyMapper.toEntity(dto);
        entity = specialtyRepository.save(entity);

        logger.info("Especialidade cadastrado com sucesso. ID: {}", entity.getId());

        return specialtyMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public SpecialtyDTO update(Long id, SpecialtyDTO dto) {
        logger.info("Iniciando atualização do especialidade. ID: {}", id);

        Specialty address = findEntityById(id);

        specialtyMapper.updateEntityFromDTO(dto, address);

        address = specialtyRepository.save(address);

        logger.info("Especialidade atualizado com sucesso. ID: {}", address.getId());

        return specialtyMapper.toDTO(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SpecialtyDTO findById(Long id) {
        logger.info("Buscando especialidade por ID: {}", id);

        Specialty address = findEntityById(id);

        logger.info("Especialidade encontrado. ID: {}", address.getId());

        return specialtyMapper.toDTO(address);
    }

    /**
     * @return
     */
    @Override
    public List<SpecialtyDTO> findAll() {
        logger.info("Listando todos os especialidades.");

        List<Specialty> addresses = specialtyRepository.findAll();

        logger.info("Total de especialidades encontrados: {}", addresses.size());

        return specialtyMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo especialidade. ID: {}", id);

        Specialty address = findEntityById(id);
        specialtyRepository.delete(address);

        logger.info("Especialidade removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Specialty findEntityById(Long id) {
        logger.debug("Consultando entidade Specialty. ID: {}", id);

        Specialty address = specialtyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Especialidade não encontrado. ID: {}", id);
                    return new NotFoundException("Especialidade não encontrado");
                });

        logger.debug("Entidade Specialty localizada. ID: {}", address.getId());

        return address;
    }
}
