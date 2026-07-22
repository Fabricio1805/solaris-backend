package com.solaris.backend.application.service.impl;

import com.solaris.backend.application.dto.PaymentDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.PaymentMapper;
import com.solaris.backend.application.repository.PaymentRepository;
import com.solaris.backend.application.service.PaymentService;
import com.solaris.backend.domain.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;


    /**
     * @param dto
     * @return
     */
    @Override
    public PaymentDTO save(PaymentDTO dto) {
        logger.info("Iniciando cadastro de pagamento.");
        
        Payment payment = paymentMapper.toEntity(dto);
        payment = paymentRepository.save(payment);
        
        logger.info("Pagamento cadastrado com sucesso. ID: {}", payment.getId());
        
        return paymentMapper.toDTO(payment);
    }

    /**
     * @param id
     * @param dto
     * @return
     */
    @Override
    public PaymentDTO update(Long id, PaymentDTO dto) {
        logger.info("Iniciando atualização do pagamento. ID: {}", id);
        
        Payment payment = findEntityById(id);

        paymentMapper.updateEntityFromDTO(dto, payment);
        payment =  paymentRepository.save(payment);

        logger.info("Pagamento atualizado com sucesso. ID: {}", payment.getId());
        
        return paymentMapper.toDTO(payment);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PaymentDTO findById(Long id) {
        logger.info("Buscando pagamento por ID: {}", id);

        Payment payment = findEntityById(id);

        logger.info("Pagamento encontrado. ID: {}", payment.getId());

        return paymentMapper.toDTO(payment);
    }

    /**
     * @return
     */
    @Override
    public List<PaymentDTO> findAll() {
        logger.info("Listando todos os endereços.");

        List<Payment> addresses = paymentRepository.findAll();

        logger.info("Total de endereços encontrados: {}", addresses.size());

        return paymentMapper.toDTOList(addresses);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        logger.info("Removendo pagamento. ID: {}", id);

        Payment payment = findEntityById(id);
        paymentRepository.delete(payment);

        logger.info("Pagamento removido com sucesso. ID: {}", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Payment findEntityById(Long id) {
        logger.debug("Consultando entidade Payment. ID: {}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Pagamento não encontrado. ID: {}", id);
                    return new NotFoundException("Pagamento não encontrado");
                });

        logger.debug("Entidade Payment localizada. ID: {}", payment.getId());

        return payment;
    }
}
