package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.AppointmentDTO;
import com.solaris.backend.application.dto.ClientDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.AppoitmentMapper;
import com.solaris.backend.application.repository.AppointmentRepository;
import com.solaris.backend.application.service.AppointmentService;
import com.solaris.backend.domain.entity.Address;
import com.solaris.backend.domain.entity.Appointment;
import com.solaris.backend.domain.entity.Client;
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
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentServiceImpl.class);


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppoitmentMapper appoitmentMapper;


    /**
     * @param dto
     * @return
     */
    @Override
    public AppointmentDTO save(AppointmentDTO dto) {
        Appointment entity = appoitmentMapper.toEntity(dto);

        entity = appointmentRepository.save(entity);

        return appoitmentMapper.toDTO(entity);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public AppointmentDTO update(Long id, AppointmentDTO dto) {
        Appointment appointment = findEntityById(id);
        appoitmentMapper.updateEntityFromDTO(dto, appointment);

        appointment = appointmentRepository.save(appointment);

        return appoitmentMapper.toDTO(appointment);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AppointmentDTO findById(Long id) {
        Appointment appointment = findEntityById(id);
        return appoitmentMapper.toDTO(appointment);
    }

    /**
     * @return
     */
    @Override
    public Page<AppointmentDTO> findAll(Pageable pageable) {
        logger.info("Listando todos os Agendamentos.");

        Page<Appointment> appointments = appointmentRepository.findAll(pageable);

        logger.info("Total de Agendamentos encontrados: {}", appointments.getSize());

        return appoitmentMapper.toDTOList(appointments);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        Appointment appointment = findEntityById(id);
        appointmentRepository.delete(appointment);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Appointment findEntityById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }
}
