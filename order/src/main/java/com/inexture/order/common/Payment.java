package com.inexture.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private long paymentId;
    private String paymentStatus;
    private String transactionId;
    private long orderId;
    private double amount;
}
