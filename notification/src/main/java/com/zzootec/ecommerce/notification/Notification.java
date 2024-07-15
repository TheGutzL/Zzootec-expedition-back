package com.zzootec.ecommerce.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzootec.ecommerce.kafka.order.OrderConfirmation;
import com.zzootec.ecommerce.kafka.payment.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private LocalDateTime notificationDate;

    @Lob
    private String orderConfirmation;

    @Lob
    private String paymentConfirmation;

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void setOrderConfirmation(OrderConfirmation orderConfirmation) {
        try {
            this.orderConfirmation = objectMapper.writeValueAsString(orderConfirmation);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing order confirmation", e);
        }
    }

    public OrderConfirmation getOrderConfirmation() {
        try {
            return objectMapper.readValue(this.orderConfirmation, OrderConfirmation.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing order confirmation", e);
        }
    }

    public void setPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        try {
            this.paymentConfirmation = objectMapper.writeValueAsString(paymentConfirmation);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing payment confirmation", e);
        }
    }

    public PaymentConfirmation getPaymentConfirmation() {
        try {
            return objectMapper.readValue(this.paymentConfirmation, PaymentConfirmation.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing payment confirmation", e);
        }
    }
}
