package com.lab.microservices.currencyconversionservice.currencyConversionController;

import com.lab.microservices.currencyconversionservice.currencyConversionDomain.CurrencyConversionBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @RequestMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quatity){
        return new CurrencyConversionBean(1L,from,to,BigDecimal.ONE,quatity,quatity,0);
    }
}
