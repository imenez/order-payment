package com.imenez.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imenez.orderservice.common.TransactionRequest;
import com.imenez.orderservice.common.TransactionResponse;
import com.imenez.orderservice.entity.Order;
import com.imenez.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public TransactionResponse saveOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {



        return orderService.saveOrder(request);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable String orderId){

        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
}
