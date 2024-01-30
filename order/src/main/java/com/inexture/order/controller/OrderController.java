package com.inexture.order.controller;

import com.inexture.order.common.Payment;
import com.inexture.order.common.TransactionRequest;
import com.inexture.order.common.TransactionResponse;
import com.inexture.order.entity.Order;
import com.inexture.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest){

        return orderService.saveOrder(transactionRequest);
    }

    @GetMapping("/")
    public List<TransactionResponse> getOrders(){

        return orderService.getOrders();
    }


}
