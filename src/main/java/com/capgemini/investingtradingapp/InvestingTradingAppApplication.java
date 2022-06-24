package com.capgemini.investingtradingapp;

import com.capgemini.investingtradingapp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestingTradingAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvestingTradingAppApplication.class, args);
    }

}
