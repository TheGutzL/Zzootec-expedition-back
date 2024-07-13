package com.zzootec.order.repository;

import com.zzootec.order.dto.OrderLineResponse;
import com.zzootec.order.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Long orderId);
}
