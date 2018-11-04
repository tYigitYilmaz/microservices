package com.lab.microservices.currencyexchangeservice.repository;

import com.lab.microservices.currencyexchangeservice.currencyExchangeDomain.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepositoy extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
