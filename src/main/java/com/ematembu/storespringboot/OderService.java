package com.ematembu.storespringboot;

public class OderService {

    private PaymentService paymentService;

    // public OderService(PaymentService paymentService) {
    //     this.paymentService = paymentService;
    // }

    public void placeOder(){
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
