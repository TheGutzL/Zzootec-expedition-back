package com.app.dto.order;

public record UserOrderResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName) {

}
