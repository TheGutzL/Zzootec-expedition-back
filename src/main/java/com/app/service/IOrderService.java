package com.app.service;

import java.util.List;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.persistence.entity.OrderStatus;

public interface IOrderService {
    List<OrderResponse> findAll();

    List<OrderResponse> findByUserId(Long userId);

    OrderResponse findById(Long id);

    OrderResponse save(OrderRequest order);

    OrderResponse update(Long id, OrderRequest order);

    void updateOrderStatus(Long id, OrderStatus status);

    void delete(Long id);
}
