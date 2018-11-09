package com.lab.microservices.accountservice.repository;

import com.lab.microservices.accountservice.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByAccountNumber (String accountNumber);

}
