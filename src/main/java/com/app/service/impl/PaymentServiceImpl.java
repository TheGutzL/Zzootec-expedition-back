package com.app.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.dto.order.OrderResponse;
import com.app.dto.payment.PaymentResponse;
import com.app.persistence.entity.OrderEntity;
import com.app.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImpl implements PaymentService {

        @Value("${stripe.api.key}")
        private String stripeSecretKey;

        @Override
        public PaymentResponse createPaymentLink(OrderResponse order) {
                Stripe.apiKey = stripeSecretKey;
                PaymentResponse res = new PaymentResponse();

                try {
                        SessionCreateParams params = SessionCreateParams.builder()
                                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                                        .setMode(SessionCreateParams.Mode.PAYMENT)
                                        .setSuccessUrl("http://localhost:5173/payment/success/" + order.orderId())
                                        .setCancelUrl("http://localhost:5173/payment/fail")
                                        .addLineItem(SessionCreateParams.LineItem.builder()
                                                        .setQuantity(1L)
                                                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                                                        .setCurrency("pen")
                                                                        .setUnitAmount(order.total()
                                                                                        .multiply(new BigDecimal(100))
                                                                                        .longValue())
                                                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                                                        .builder()
                                                                                        .setName("zzootec")
                                                                                        .build())
                                                                        .build())
                                                        .build())
                                        .build();

                        Session session = Session.create(params);

                        res.setPayment_url(session.getUrl());

                } catch (StripeException e) {
                        throw new RuntimeException("Error creating payment link");
                }

                return res;
        }

}
