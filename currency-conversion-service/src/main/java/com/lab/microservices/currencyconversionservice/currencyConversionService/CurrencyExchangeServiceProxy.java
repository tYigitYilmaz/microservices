package com.lab.microservices.currencyconversionservice.currencyConversionService;

import com.lab.microservices.currencyconversionservice.currencyConversionDomain.CurrencyConversionBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {
    @RequestMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}