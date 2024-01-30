package com.inexture.order.service;

import com.inexture.order.common.Payment;
import com.inexture.order.common.TransactionRequest;
import com.inexture.order.common.TransactionResponse;
import com.inexture.order.entity.Order;
import com.inexture.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        String message = "";
        Order order= transactionRequest.getOrder();
        Payment payment= transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/addPayment", payment, payment.getClass());
        message = paymentResponse.getPaymentStatus().equalsIgnoreCase("Success")?"Payment Successfully and order placed":"Payment Failure , Order added to cart";
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getTransactionId(),paymentResponse.getAmount(), message);
    }

    public List<TransactionResponse> getOrders(){
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        orderList.stream().forEach(order -> {
            String message = "";
            Payment paymentResponse = template.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), Payment.class);
            message = paymentResponse.getPaymentStatus().equalsIgnoreCase("Success")?"Payment Successfully and order placed":"Payment Failure , Order added to cart";
            transactionResponses.add(new TransactionResponse(order, paymentResponse.getTransactionId(),paymentResponse.getAmount(), message));
        });
        return transactionResponses;
    }
}
