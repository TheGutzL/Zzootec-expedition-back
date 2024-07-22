package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.persistence.entity.OrderStatus;

public interface IOrderService {
    List<OrderResponse> findAll();

    Page<OrderResponse> findAllPaginated(Pageable pageable);

    List<OrderResponse> findByUserId(Long userId);

    OrderResponse findById(Long id);

    OrderResponse save(OrderRequest order);

    OrderResponse update(Long id, OrderRequest order);

    void updateOrderStatus(Long id, OrderStatus status);

    void delete(Long id);
}
