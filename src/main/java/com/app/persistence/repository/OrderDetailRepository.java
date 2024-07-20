package com.app.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.persistence.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findAllByOrderId(Long orderId);
}
