package com.zzootec.order.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
