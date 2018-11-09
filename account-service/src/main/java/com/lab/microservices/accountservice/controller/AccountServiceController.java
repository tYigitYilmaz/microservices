package com.lab.microservices.accountservice.controller;

import com.lab.microservices.accountservice.Account;
import com.lab.microservices.accountservice.repository.AccountRepository;
import com.lab.microservices.accountservice.service.TrasactionServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountServiceController {

    private TrasactionServiceProxy trasactionServiceProxy;
    private AccountRepository accountRepository;
    private Environment environment;

    @Autowired
    public void SetTrasactionServiceProxy(TrasactionServiceProxy trasactionServiceProxy){
        this.trasactionServiceProxy=trasactionServiceProxy;
    }

    public TrasactionServiceProxy getTrasactionServiceProxy() {
        return trasactionServiceProxy;
    }

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    @RequestMapping(value = "/accountNumber/{accountNumber}", method = RequestMethod.GET)
    public Account retrieveAccountNumber(@PathVariable String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        /* logger.info("{}",exchangeValue);

        public ExchangeValue retrieveExhangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepositoy.findByFromAndTo(from,to);

         */
        return account;
    }

    @GetMapping(value = "/transaction/accountFrom/{accountFrom}/accountTo/{accountTo}/{accountBalance1}/{accountBalance2}")
    public boolean transactionFeign(@PathVariable String accountFrom, @PathVariable String accountTo
            , @PathVariable(value = "accountBalance1") BigDecimal accountBalance1
            , @PathVariable(value = "accountBalance2") BigDecimal accountBalance2, @PathVariable BigDecimal quantity) {

        Account account1 = accountRepository.findByAccountNumber(accountFrom);
        Account account2 = accountRepository.findByAccountNumber(accountTo);
        Account response = trasactionServiceProxy.transactionFeign(accountFrom, accountTo,accountBalance1,accountBalance2);

        accountRepository.save(account1);
        accountRepository.save(account2);

        return true;

    }
}


