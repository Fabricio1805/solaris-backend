package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.EmployeeDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.EmployeeMapper;
import com.solaris.backend.application.repository.EmployeeRepository;
import com.solaris.backend.application.service.EmployeeService;
import com.solaris.backend.domain.entity.Company;
import com.solaris.backend.domain.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository  employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * @param dto
     * @return
     */
    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        Employee entity = employeeMapper.toEntity(dto);

        entity = employeeRepository.save(entity);

        return employeeMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee employee = findEntityById(id);
        employeeMapper.updateEntityFromDTO(dto, employee);

        employee = employeeRepository.save(employee);

        return employeeMapper.toDTO(employee);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = findEntityById(id);
        return employeeMapper.toDTO(employee);
    }

    /**
     * @return
     */
    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.toDTOList(employeeRepository.findAll());
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        Employee employee = findEntityById(id);
        employeeRepository.delete(employee);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employee findEntityById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Empregado não encontrado"));
    }
}
