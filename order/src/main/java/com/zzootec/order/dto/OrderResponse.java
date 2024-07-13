package com.zzootec.order.dto;

import com.zzootec.order.models.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long customerId
) {
}
