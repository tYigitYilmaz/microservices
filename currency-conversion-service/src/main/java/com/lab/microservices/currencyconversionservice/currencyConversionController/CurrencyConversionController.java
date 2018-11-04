package com.lab.microservices.currencyconversionservice.currencyConversionController;

import com.lab.microservices.currencyconversionservice.currencyConversionDomain.CurrencyConversionBean;
import com.lab.microservices.currencyconversionservice.currencyConversionService.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Autowired
    public void setCurrencyExchangeServiceProxy(CurrencyExchangeServiceProxy currencyExchangeServiceProxy){
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    public CurrencyExchangeServiceProxy getCurrencyExchangeServiceProxy() {
        return currencyExchangeServiceProxy;
    }

    @RequestMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exhange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();
        return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
                quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    @RequestMapping(value = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from,to);

        return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
                quantity.multiply(response.getConversionMultiple()),response.getPort());
    }


}
