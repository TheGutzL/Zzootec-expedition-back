package com.zzootec.order.payment;

import com.zzootec.order.dto.CustomerResponse;
import com.zzootec.order.models.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
