package com.ematembu.storespringboot;

public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount){
        System.out.println("PAYPAL");
        System.out.println("Amount: " + amount);
    }
}
