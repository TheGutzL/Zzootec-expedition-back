package com.app.service;

import java.util.List;

import com.app.dto.orderdetail.OrderDetailRequest;
import com.app.dto.orderdetail.OrderDetailResponse;

public interface IOrderDetailService {
    List<OrderDetailResponse> findAll();

    List<OrderDetailResponse> findAllByOrderId(Long orderId);

    OrderDetailResponse findById(Long id);

    OrderDetailResponse save(OrderDetailRequest ordenDetailRequest);

    OrderDetailResponse update(Long id, OrderDetailRequest ordenDetailRequest);

    void delete(Long id);
}
