package com.ematembu.storespringboot;

public class OderService {

    private final PaymentService paymentService;

    public OderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOder(){
        paymentService.processPayment(10);
    }
}
