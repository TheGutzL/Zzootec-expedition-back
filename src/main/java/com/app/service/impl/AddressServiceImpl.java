package com.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.address.AddressRequest;
import com.app.dto.address.AddressResponse;
import com.app.mapper.AddressMapper;
import com.app.persistence.entity.AddressEntity;
import com.app.persistence.repository.AddressRepository;
import com.app.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AddressResponse> findAll() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressResponse> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId).stream()
                .map(AddressMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse findOneById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public AddressResponse save(AddressRequest address) {
        AddressEntity addressEntity = AddressMapper.dtoToEntity(address);
        return AddressMapper.entityToDto(addressRepository.save(addressEntity));
    }

    @Override
    public AddressResponse update(Long id, AddressRequest address) {
        AddressEntity addressFound = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        addressFound.setStreet(address.street());
        addressFound.setCity(address.city());
        addressFound.setState(address.state());
        addressFound.setZipCode(address.zipCode());
        addressFound.setCountry(address.country());

        addressFound.setUser(userRepository.findById(address.userId()).orElseThrow(() -> new RuntimeException("User not found")));

        return AddressMapper.entityToDto(addressRepository.save(addressFound));
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
