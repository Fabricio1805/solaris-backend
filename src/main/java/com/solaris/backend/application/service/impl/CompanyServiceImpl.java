package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.ClientDTO;
import com.solaris.backend.application.dto.CompanyDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.CompanyMapper;
import com.solaris.backend.application.repository.CompanyRepository;
import com.solaris.backend.application.service.CompanyService;
import com.solaris.backend.domain.entity.Client;
import com.solaris.backend.domain.entity.Company;
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
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * @param dto
     * @return
     */
    @Override
    public CompanyDTO save(CompanyDTO dto) {
        Company entity = companyMapper.toEntity(dto);

        entity = companyRepository.save(entity);

        return companyMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company company = findEntityById(id);

        companyMapper.updateEntityFromDTO(dto, company);

        company = companyRepository.save(company);

        return companyMapper.toDTO(company);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CompanyDTO findById(Long id) {
        Company company = findEntityById(id);
        return companyMapper.toDTO(company);
    }

    /**
     * @return
     */
    @Override
    public Page<CompanyDTO> findAll(Pageable pageable) {
        logger.info("Listando todos os Empresas.");

        Page<Company> companies = companyRepository.findAll(pageable);

        logger.info("Total de Empresas encontrados: {}", companies.getSize());

        return companyMapper.toDTOList(companies);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        Company company = findEntityById(id);
        companyRepository.delete(company);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Company findEntityById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Empresa não encontrado"));
    }
}
