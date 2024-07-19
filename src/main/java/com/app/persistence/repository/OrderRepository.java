package com.app.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.persistence.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}

