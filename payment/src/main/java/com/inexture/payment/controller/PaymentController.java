package com.inexture.payment.controller;

import com.inexture.payment.entity.Payment;
import com.inexture.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment paymentHistory(@PathVariable long orderId){
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }
}
