package com.ematembu.storespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreSpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreSpringbootApplication.class, args);
        var orderService = context.getBean(OderService.class);
        orderService.placeOder();
    }
}
