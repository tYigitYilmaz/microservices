package com.lab.microservices.accountservice.service;

import com.lab.microservices.accountservice.Account;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="transaction-service")
public interface TrasactionServiceProxy {

    @GetMapping(value = "/transaction/accountFrom/{accountFrom}/accountTo/{accountTo}/{accountBalance1}/{accountBalance2}")
    Account transactionFeign
            (@PathVariable(value = "accountFrom") String accountFrom, @PathVariable(value = "accountTo") String accountTo
                    , @PathVariable(value = "accountBalance1") BigDecimal accountBalance1
                    , @PathVariable(value = "accountBalance2") BigDecimal accountBalance2
            );
}

