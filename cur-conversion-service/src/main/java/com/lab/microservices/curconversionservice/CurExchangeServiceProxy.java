package com.lab.microservices.curconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurExchangeServiceProxy {
    //@GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
    /*@RequestMapping(value = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET,produces = {
            MediaType.APPLICATION_JSON_VALUE})*/
    @GetMapping(value = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurConversionBean retrieveExchangeValue
    (@PathVariable(value = "from") String from, @PathVariable(value = "to") String to);
}