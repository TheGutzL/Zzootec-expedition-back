package com.zzootec.customer.dto;

import com.zzootec.customer.model.Address;

public record CustomerResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
