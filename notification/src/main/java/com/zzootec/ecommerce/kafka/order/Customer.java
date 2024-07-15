package com.zzootec.ecommerce.kafka.order;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
