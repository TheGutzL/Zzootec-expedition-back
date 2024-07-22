package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.order.OrderRequest;
import com.app.dto.order.OrderResponse;
import com.app.dto.orderdetail.OrderDetailResponse;
import com.app.dto.payment.PaymentResponse;
import com.app.service.IOrderDetailService;
import com.app.service.IOrderService;
import com.app.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private PagedResourcesAssembler<OrderResponse> assembler;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        try {
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated")
    public PagedModel<EntityModel<OrderResponse>> getAllPaginated(
            Pageable pageable) {
        Page<OrderResponse> orders = orderService.findAllPaginated(pageable);
        return assembler.toModel(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOneById(
            @PathVariable Long id) {
        try {
            return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<OrderDetailResponse>> getDetailsByOrderId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(orderDetailService.findAllByOrderId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long idUser) {
        try {
            return new ResponseEntity<>(orderService.findByUserId(idUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@RequestBody @Valid OrderRequest orderRequest) {
        try {
            OrderResponse orderResponse = orderService.save(orderRequest);
            PaymentResponse res = paymentService.createPaymentLink(orderResponse);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid OrderRequest orderRequest) {
        try {
            return new ResponseEntity<>(orderService.update(id, orderRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        try {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
