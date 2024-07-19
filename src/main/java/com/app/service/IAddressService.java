package com.app.service;

import java.util.List;

import com.app.dto.address.AddressRequest;
import com.app.dto.address.AddressResponse;

public interface IAddressService {
    List<AddressResponse> findAll();

    List<AddressResponse> findAllByUserId(Long userId);

    AddressResponse findOneById(Long id);

    AddressResponse save(AddressRequest address);

    AddressResponse update(Long id, AddressRequest address);

    void delete(Long id);
}
