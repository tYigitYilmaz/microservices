package com.lab.microservices.transactionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TransationController {

    private AccountServiceProxy accountServiceProxy;

    @Autowired
    public void setAccountServiceProxy(AccountServiceProxy accountServiceProxy) {
        this.accountServiceProxy = accountServiceProxy;
    }

    public AccountServiceProxy getAccountServiceProxy() {
        return accountServiceProxy;
    }

    @GetMapping(value = "/transaction/accountFrom/{accountFrom}/accountTo/{accountTo}/{accountBalance1}/{accountBalance2/quantity/{quantity}")
    public Transaction transactionFeign(@PathVariable String accountFrom, @PathVariable String accountTo
            , @PathVariable(value = "accountBalance1") BigDecimal accountBalance1
            , @PathVariable(value = "accountBalance2") BigDecimal accountBalance2,
              @PathVariable(value = "quantity") BigDecimal quantity)
             {
        Transaction response = accountServiceProxy.transactionFeign(accountFrom, accountTo,accountBalance1,accountBalance2);

        return new Transaction(response.getId(),quantity,accountBalance1,response.getPort());
    }
}
