package com.zzootec.order.service;

import com.zzootec.order.dto.OrderLineRequest;
import com.zzootec.order.dto.OrderLineResponse;
import com.zzootec.order.models.Order;
import com.zzootec.order.models.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
