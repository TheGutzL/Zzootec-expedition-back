package com.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.app.persistence.repository.AddressRepository;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.mapper.OrderMapper;
import com.app.persistence.entity.AddressEntity;
import com.app.persistence.entity.OrderEntity;
import com.app.persistence.entity.OrderStatus;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.OrderRepository;
import com.app.service.IOrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Long id) {
        return OrderMapper.entityToDto(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public OrderResponse save(OrderRequest order) {
        return OrderMapper.entityToDto(orderRepository.save(OrderMapper.dtoToEntity(order)));
    }

    @Override
    @Transactional
    public OrderResponse update(Long id, OrderRequest orderRequest) {
        OrderEntity orderFound = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        // Actualizar los campos básicos
        orderFound.setTotal(orderRequest.total());
        orderFound.setStatus(OrderStatus.valueOf(orderRequest.status()));

        // Buscar y validar UserEntity
        UserEntity userFound = userRepository.findById(orderRequest.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderRequest.userId()));
        orderFound.setUser(userFound);

        // Buscar y validar AddressEntity
        AddressEntity addressFound = addressRepository.findById(orderRequest.shippingAddressId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Address not found with id: " + orderRequest.shippingAddressId()));
        orderFound.setShippingAddress(addressFound);

        // Guardar la entidad actualizada
        OrderEntity updatedOrder = orderRepository.save(orderFound);

        // Convertir y devolver OrderResponse
        return OrderMapper.entityToDto(updatedOrder);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
