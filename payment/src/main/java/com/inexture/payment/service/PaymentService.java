package com.inexture.payment.service;

import com.inexture.payment.entity.Payment;
import com.inexture.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        // api should be third party payment gateway
        return new Random().nextBoolean()?"Success":"false";
    }

    public Payment findPaymentHistoryByOrderId(long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
