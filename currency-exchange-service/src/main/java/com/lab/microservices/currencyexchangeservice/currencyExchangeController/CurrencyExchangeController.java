package com.lab.microservices.currencyexchangeservice.currencyExchangeController;

import com.lab.microservices.currencyexchangeservice.currencyExchangeDomain.ExchangeValue;
import com.lab.microservices.currencyexchangeservice.repository.ExchangeValueRepositoy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Environment environment;
    private ExchangeValueRepositoy exchangeValueRepositoy;
  /*  private Logger logger = LoggerFactory.getLogger(this.getClass());*/

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Autowired
    public void setExchangeValueRepositoy(ExchangeValueRepositoy exchangeValueRepositoy) {
        this.exchangeValueRepositoy = exchangeValueRepositoy;
    }

    public ExchangeValueRepositoy getExchangeValueRepositoy() {
        return exchangeValueRepositoy;
    }

    @RequestMapping(value = "/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
    public ExchangeValue retrieveExhangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepositoy.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
       /* logger.info("{}",exchangeValue);
*/
        return exchangeValue;
    }
}
