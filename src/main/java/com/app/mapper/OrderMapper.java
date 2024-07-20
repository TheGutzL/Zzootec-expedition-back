package com.app.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.dto.order.UserOrderResponse;
import com.app.persistence.entity.AddressEntity;
import com.app.persistence.entity.OrderDetailEntity;
import com.app.persistence.entity.OrderEntity;
import com.app.persistence.entity.OrderStatus;
import com.app.persistence.entity.ProductEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.AddressRepository;
import com.app.persistence.repository.ProductRepository;
import com.app.persistence.repository.UserRepository;

@Component
public class OrderMapper {

    private static UserRepository userRepository = null;

    private static ProductRepository productRepository = null;

    private static AddressRepository addressRepository = null;

    public static OrderResponse entityToDto(OrderEntity orderEntity) {
        UserOrderResponse userOrderResponse = convertUserEntityToUserOrderResponse(orderEntity.getUser());
        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getTotal(),
                orderEntity.getStatus(),
                userOrderResponse,
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
        orderEntity.setStatus(orderStatus);

        AtomicReference<BigDecimal> total = new AtomicReference<BigDecimal>(BigDecimal.ZERO);

        List<OrderDetailEntity> orderDetails = orderRequest.products().stream()
                .map(productQuantity -> {
                    ProductEntity productEntity = productRepository.findById(productQuantity.productId()).orElseThrow();

                    BigDecimal price = BigDecimal.valueOf(productEntity.getPrice());
                    int quantity = productQuantity.quantity();
                    BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));

                    total.updateAndGet(t -> t.add(totalPrice));

                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                    orderDetailEntity.setOrder(orderEntity);
                    orderDetailEntity.setProduct(productEntity);
                    orderDetailEntity.setQuantity(productQuantity.quantity());
                    orderDetailEntity.setSubtotal(totalPrice);

                    return orderDetailEntity;
                }).collect(Collectors.toList());

        orderEntity.setTotal(total.get());
        orderEntity.setOrderDetails(orderDetails);

        return orderEntity;
    }

    private static UserOrderResponse convertUserEntityToUserOrderResponse(UserEntity userEntity) {
        return new UserOrderResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName());
    }

    public OrderMapper(UserRepository userRepository, AddressRepository addressRepository,
            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

}
