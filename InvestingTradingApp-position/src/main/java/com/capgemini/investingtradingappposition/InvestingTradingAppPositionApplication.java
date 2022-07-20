package com.capgemini.investingtradingappposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class InvestingTradingAppPositionApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingTradingAppPositionApplication.class, args);
    }

}
