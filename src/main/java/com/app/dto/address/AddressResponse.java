package com.app.dto.address;

public record AddressResponse(
        Long addressId,
        String street,
        String city,
        String state,
        String country,
        String zipCode) {

}
