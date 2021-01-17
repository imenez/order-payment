package com.imenez.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imenez.paymentservice.entity.Payment;
import com.imenez.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Transactional
    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("Payment service Request : {}", new ObjectMapper().writeValueAsString(payment));
        paymentRepository.save(payment);

        return payment;
    }

    public String paymentProcessing(){

        return new Random().nextBoolean()?"success":"false";

    }

    @Transactional
    public Payment findPaymentHistoryByOrderId(String orderId) throws JsonProcessingException {
        Payment payment = paymentRepository.findPaymentHistoryByOrderId(orderId);

        log.info("Payment findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));

        return payment;
    }
}
