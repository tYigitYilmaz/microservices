package com.lab.microservices.transactionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="transaction-service")
public interface AccountServiceProxy {

        @GetMapping(value = "/transaction/accountFrom/{accountFrom}/accountTo/{accountTo}/{accountBalance1}/{accountBalance2}")
        Transaction transactionFeign
                (@PathVariable(value = "accountFrom") String accountFrom, @PathVariable(value = "accountTo") String accountTo
                        , @PathVariable(value = "accountBalance1") BigDecimal accountBalance1
                        , @PathVariable(value = "accountBalance2") BigDecimal accountBalance2
                );
    }

