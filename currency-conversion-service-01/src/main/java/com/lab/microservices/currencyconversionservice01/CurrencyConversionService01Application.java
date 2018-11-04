package com.lab.microservices.currencyconversionservice01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.lab.microservices.currencyconversionservice01")
public class CurrencyConversionService01Application {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionService01Application.class, args);
    }
}
