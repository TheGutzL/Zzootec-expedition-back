package com.app.service;

import com.app.dto.payment.PaymentResponse;
import com.app.persistence.entity.OrderEntity;

public interface PaymentService {

    public PaymentResponse createPaymentLink(OrderEntity order);
    

}
