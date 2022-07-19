package com.capgemini.investingtradingappuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class InvestingTradingAppUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingTradingAppUserApplication.class, args);
    }

}
