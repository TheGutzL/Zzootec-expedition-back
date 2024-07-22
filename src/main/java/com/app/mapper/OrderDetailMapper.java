
package com.app.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.app.dto.orderdetail.OrderDetailRequest;
import com.app.dto.orderdetail.OrderDetailResponse;
import com.app.dto.product.ProductResponse;
import com.app.persistence.entity.OrderDetailEntity;
import com.app.persistence.entity.OrderEntity;
import com.app.persistence.entity.ProductEntity;
import com.app.persistence.repository.OrderRepository;
import com.app.persistence.repository.ProductRepository;

@Component
public class OrderDetailMapper {

    private static ProductRepository productRepository = null;

    private static OrderRepository orderRepository = null;

    public static OrderDetailEntity requestToEntity(OrderDetailRequest ordenDetailRequest) {
        ProductEntity product = productRepository.findById(ordenDetailRequest.productId()).orElseThrow();

        OrderEntity order = orderRepository.findById(ordenDetailRequest.orderId()).orElseThrow();

        OrderDetailEntity detailOrder = new OrderDetailEntity();
        detailOrder.setOrder(order);
        detailOrder.setProduct(product);
        detailOrder.setQuantity(ordenDetailRequest.quantity());
        detailOrder.setSubtotal(BigDecimal.valueOf(ordenDetailRequest.quantity() * product.getPrice()));

        return detailOrder;
    }

    public static OrderDetailResponse entityToResponse(OrderDetailEntity ordenDetailEntity) {

        ProductResponse product = ProductMapper.entityToDto(ordenDetailEntity.getProduct());

        return new OrderDetailResponse(
                ordenDetailEntity.getId(),
                ordenDetailEntity.getOrder().getId(),
                product,
                ordenDetailEntity.getQuantity(),
                ordenDetailEntity.getSubtotal());
    }

    public OrderDetailMapper(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

}
