package com.ematembu.storespringboot;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service // This is an alias for Component
public class OderService {

    private PaymentService paymentService;

    public OderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOder(){
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
