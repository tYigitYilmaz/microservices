package com.lab.microservices.curconversionservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CurConversionController {


    private CurExchangeServiceProxy curExchangeServiceProxy;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public void SetCurExchangeServiceProxy(CurExchangeServiceProxy curExchangeServiceProxy){
        this.curExchangeServiceProxy=curExchangeServiceProxy;
    }

    public CurExchangeServiceProxy getCurExchangeServiceProxy() {
        return curExchangeServiceProxy;
    }

    @RequestMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
    public CurConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exhange/from/{from}/to/{to}",
                CurConversionBean.class,
                uriVariables);

        CurConversionBean response = responseEntity.getBody();

        return new CurConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @RequestMapping(value = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
    public CurConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurConversionBean response = curExchangeServiceProxy.retrieveExchangeValue(from, to);

        logger.info("{}",response);
        return new CurConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());

    }
}
