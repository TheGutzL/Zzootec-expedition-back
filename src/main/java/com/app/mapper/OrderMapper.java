package com.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.persistence.entity.AddressEntity;
import com.app.persistence.entity.OrderEntity;
import com.app.persistence.entity.OrderStatus;
import com.app.persistence.entity.ProductEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.AddressRepository;
import com.app.persistence.repository.UserRepository;

@Component
public class OrderMapper {

    private static UserRepository userRepository = null;

    private static AddressRepository addressRepository = null;

    public static OrderResponse entityToDto(OrderEntity orderEntity) {

        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getTotal(),
                orderEntity.getStatus(),
                UserMapper.entityToDto(orderEntity.getUser()),
                AddressMapper.entityToDto(orderEntity.getShippingAddress()),
                orderEntity.getCreatedAt());
    }

    public static OrderEntity dtoToEntity(OrderRequest orderRequest) {
        UserEntity userEntity = userRepository.findById(orderRequest.userId()).orElseThrow();
        AddressEntity addressEntity = addressRepository.findById(orderRequest.shippingAddressId()).orElseThrow();
        OrderStatus orderStatus = OrderStatus.valueOf(orderRequest.status().toUpperCase());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(userEntity);
        orderEntity.setShippingAddress(addressEntity);
        orderEntity.setTotal(orderRequest.total());
        orderEntity.setStatus(orderStatus);

        return orderEntity;
    }

    public OrderMapper(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

}
