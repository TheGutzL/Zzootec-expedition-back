package com.app.service;

import java.util.List;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;

public interface IOrderService {
    List<OrderResponse> findAll();

    OrderResponse findById(Long id);

    OrderResponse save(OrderRequest order);

    OrderResponse update(Long id, OrderRequest order);

    void delete(Long id);
}
