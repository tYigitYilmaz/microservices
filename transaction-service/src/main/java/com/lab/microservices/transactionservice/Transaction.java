package com.lab.microservices.transactionservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;


        private BigDecimal amount;
        private BigDecimal availableBalance;
        private int port;

        /*@ManyToOne
        @JoinColumn(name = "primary_account_id")
        private PrimaryAccount primaryAccount;
*/

        public Transaction(Long id, BigDecimal amount, BigDecimal availableBalance,int port) {
            this.id=id;
            this.amount = amount;
            this.availableBalance = availableBalance;
            this.port= port;
        }



    @ManyToOne
        @JoinColumn(name = "transaction_id")
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getAvailableBalance() {
            return availableBalance;
        }

        public void setAvailableBalance(BigDecimal availableBalance) {
            this.availableBalance = availableBalance;
        }

        @JsonIgnore
         public int getPort() {
        return port;
        }
        public void setPort(int port) {
        this.port = port;
    }

    }

