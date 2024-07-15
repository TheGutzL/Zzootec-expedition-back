package com.zzootec.order.service;

import com.zzootec.order.dto.OrderLineRequest;
import com.zzootec.order.dto.OrderLineResponse;
import com.zzootec.order.models.OrderLine;
import com.zzootec.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Long saveOrderLine(OrderLineRequest request) {
        OrderLine order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
