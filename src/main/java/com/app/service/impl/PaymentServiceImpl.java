package com.app.service.impl;

import org.springframework.beans.factory.annotation.Value;

import com.app.dto.payment.PaymentResponse;
import com.app.persistence.entity.OrderEntity;
import com.app.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse createPaymentLink(OrderEntity order) {

    }
    
}
