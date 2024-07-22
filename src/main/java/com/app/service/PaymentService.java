package com.app.service;

import com.app.dto.order.OrderResponse;
import com.app.dto.payment.PaymentResponse;
import com.app.persistence.entity.OrderEntity;

import lombok.Data;

public interface PaymentService {

    public PaymentResponse createPaymentLink(OrderResponse order);
    

}
