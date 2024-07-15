package com.zzootec.order.kafka;

import com.zzootec.order.dto.CustomerResponse;
import com.zzootec.order.dto.PurchaseResponse;
import com.zzootec.order.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
