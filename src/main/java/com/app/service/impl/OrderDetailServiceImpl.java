package com.app.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.orderdetail.OrderDetailRequest;
import com.app.dto.orderdetail.OrderDetailResponse;
import com.app.mapper.OrderDetailMapper;
import com.app.persistence.entity.OrderDetailEntity;
import com.app.persistence.entity.OrderEntity;
import com.app.persistence.entity.ProductEntity;
import com.app.persistence.repository.OrderDetailRepository;
import com.app.persistence.repository.OrderRepository;
import com.app.persistence.repository.ProductRepository;
import com.app.service.IOrderDetailService;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetailResponse> findAll() {
        return detailRepository.findAll().stream()
                .map(OrderDetailMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailResponse> findAllByOrderId(Long orderId) {
        return detailRepository.findAllByOrderId(orderId).stream()
                .map(OrderDetailMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse findById(Long id) {
        return OrderDetailMapper.entityToResponse(
                detailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found")));
    }

    @Override
    public OrderDetailResponse save(OrderDetailRequest ordenDetailRequest) {

        OrderDetailEntity savedOrderDetail = detailRepository
                .save(OrderDetailMapper.requestToEntity(ordenDetailRequest));

        OrderEntity order = orderRepository.findById(ordenDetailRequest.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        BigDecimal newTotal = order.getOrderDetails().stream()
                .map(OrderDetailEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(newTotal);
        orderRepository.save(order);

        return OrderDetailMapper.entityToResponse(savedOrderDetail);
    }

    @Override
    public OrderDetailResponse update(Long id, OrderDetailRequest ordenDetailRequest) {
        OrderDetailEntity orderDetailFound = detailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        orderDetailFound.setOrder(orderRepository.findById(ordenDetailRequest.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found")));

        ProductEntity productFound = productRepository.findById(ordenDetailRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        orderDetailFound.setProduct(productFound);
        orderDetailFound.setQuantity(ordenDetailRequest.quantity());

        Double subtotal = productFound.getPrice() * ordenDetailRequest.quantity();
        orderDetailFound.setSubtotal(BigDecimal.valueOf(subtotal));

        OrderDetailEntity savedOrderDetail = detailRepository.save(orderDetailFound);

        OrderEntity updatedOrder = orderDetailFound.getOrder();

        BigDecimal newTotal = updatedOrder.getOrderDetails().stream()
                .map(OrderDetailEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        updatedOrder.setTotal(newTotal);
        orderRepository.save(updatedOrder);

        return OrderDetailMapper.entityToResponse(savedOrderDetail);
    }

    @Override
    public void delete(Long id) {
        OrderDetailEntity orderDetail = detailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        OrderEntity order = orderDetail.getOrder();

        detailRepository.deleteById(id);

        BigDecimal newTotal = order.getOrderDetails().stream()
                .map(OrderDetailEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(newTotal);
        orderRepository.save(order);
    }

}
