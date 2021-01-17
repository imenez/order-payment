package com.imenez.paymentservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.imenez.paymentservice.entity.Payment;
import com.imenez.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addPayment")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) throws JsonProcessingException {


        return ResponseEntity.ok(paymentService.savePayment(payment));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> findPaymentHistoryByPaymentId(@PathVariable String orderId) throws JsonProcessingException {


        return ResponseEntity.ok(paymentService.findPaymentHistoryByOrderId(orderId));
    }


}
