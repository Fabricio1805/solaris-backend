package com.solaris.backend.application.mapper;

import com.solaris.backend.application.dto.PaymentDTO;
import com.solaris.backend.domain.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                AppoitmentMapper.class
        }
)
public interface PaymentMapper extends BaseMapper<PaymentDTO, Payment> {
}





