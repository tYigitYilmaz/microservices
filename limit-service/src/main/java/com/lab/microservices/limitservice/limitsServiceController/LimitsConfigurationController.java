package com.lab.microservices.limitservice.limitsServiceController;

import com.lab.microservices.limitservice.Configuration;
import com.lab.microservices.limitservice.dao.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    private Configuration configuration;

    @Autowired
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @RequestMapping(value = "/limits", method = RequestMethod.GET)
    public LimitsConfiguration retrieveLimitsFromConfigurations() {
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}