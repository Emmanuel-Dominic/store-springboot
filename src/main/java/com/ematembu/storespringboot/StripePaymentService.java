package com.ematembu.storespringboot;

import org.springframework.stereotype.Service;

@Service // You can choose to use Component but component is commonly used under the utils
public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount){
        System.out.println("STRIPE");
        System.out.println("Amount: " + amount);
    }
}
