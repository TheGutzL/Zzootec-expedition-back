package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.dto.orderdetail.OrderDetailRequest;
import com.app.dto.orderdetail.OrderDetailResponse;
import com.app.service.IOrderDetailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/order-details")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping()
    public ResponseEntity<List<OrderDetailResponse>> getAll() {
        try {
            return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> getOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(orderDetailService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderDetailResponse> create(@RequestBody @Valid OrderDetailRequest detailRequest) {
        try {
            return new ResponseEntity<>(orderDetailService.save(detailRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid OrderDetailRequest detailRequest) {
        try {
            return new ResponseEntity<>(orderDetailService.update(id, detailRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        try {
            orderDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
