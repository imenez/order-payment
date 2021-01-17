package com.imenez.paymentservice.repository;

import com.imenez.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {


    Payment findPaymentHistoryByOrderId(String orderId);
}
