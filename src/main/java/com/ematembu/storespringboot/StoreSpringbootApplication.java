package com.ematembu.storespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringbootApplication {

    public static void main(String[] args) {
        // SpringApplication.run(StoreSpringbootApplication.class, args);
        var orderService = new OderService(new StripePaymentService());
        orderService.placeOder();
    }
}
