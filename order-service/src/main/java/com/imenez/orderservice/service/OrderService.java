package com.imenez.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imenez.orderservice.common.Payment;
import com.imenez.orderservice.common.TransactionRequest;
import com.imenez.orderservice.common.TransactionResponse;
import com.imenez.orderservice.entity.Order;
import com.imenez.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger log = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {

        String message;
        Order order = request.getOrder();
        Payment payment = request.getPayment();

        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("OrderService request: {}", new ObjectMapper().writeValueAsString(request));

        //rest calling for payment
        Payment paymentResponse =
                restTemplate.postForObject("http://PAYMENT-SERVICE/payments/addPayment", payment, Payment.class);

        log.info("PaymentService response: {}", new ObjectMapper().writeValueAsString(paymentResponse));

        orderRepository.save(order);

        message = paymentResponse.getPaymentStatus().equals("success")?"payment is ok":"failed";

        return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), message);
    }

    @Transactional
    public Optional<Order> getOrder(String orderId){

        return orderRepository.findById(orderId);
    }

}
