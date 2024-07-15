package com.zzootec.order.dto;

public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
