package com.capgemini.investingtradingappnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients(basePackages = {"com.capgemini.investingtradingappuserclient", "com.capgemini.investingtradingapppositionclient"})
@EnableEurekaClient
public class InvestingTradingAppNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingTradingAppNotificationApplication.class, args);
    }

}
