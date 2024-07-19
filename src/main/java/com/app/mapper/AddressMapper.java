package com.app.mapper;

import com.app.dto.address.AddressRequest;
import com.app.dto.address.AddressResponse;
import com.app.persistence.entity.AddressEntity;
import com.app.persistence.entity.UserEntity;

public class AddressMapper {

    public static AddressResponse entityToDto(AddressEntity addressEntity) {
        return new AddressResponse(
                addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getCountry(),
                addressEntity.getZipCode());
    }

    public static AddressEntity dtoToEntity(AddressRequest addressRequest) {
        UserEntity user = UserEntity.builder().id(addressRequest.userId()).build();

        return AddressEntity.builder()
                .street(addressRequest.street())
                .city(addressRequest.city())
                .state(addressRequest.state())
                .zipCode(addressRequest.zipCode())
                .country(addressRequest.country())
                .user(user)
                .build();
    }

}
