package com.capgemini.investingtradingappuser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;

@EnableCaching
@EnableKafka
@EnableEurekaClient
@SpringBootApplication
public class InvestingTradingAppUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingTradingAppUserApplication.class, args);
    }

}
