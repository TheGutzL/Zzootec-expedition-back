package com.zzootec.ecommerce.notification;

import com.zzootec.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}
