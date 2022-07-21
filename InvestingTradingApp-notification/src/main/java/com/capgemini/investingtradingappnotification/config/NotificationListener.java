package com.capgemini.investingtradingappnotification.config;

import com.capgemini.investingtradingapppositionclient.client.CompanyClient;
import com.capgemini.investingtradingappuserclient.client.UserClient;
import com.capgemini.investingtradingappuserclient.event.PositionBoughtEvent;
import com.capgemini.investingtradingappuserclient.event.PositionSoldEvent;
import com.capgemini.investingtradingappuserclient.event.UserDeletedEvent;
import com.capgemini.investingtradingappuserclient.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Component
public class NotificationListener {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CompanyClient companyClient;

    @KafkaListener(topics = "user-create", groupId = "user")
    public void listen(@Payload UserRegisteredEvent userRegisteredEvent) {
        log.info("User of email: " + userRegisteredEvent.getEmail() + " has been created" +
                "\nDetails: " +
                "\nFirst name: " + userClient.read(Map.of("email", userRegisteredEvent.getEmail(),
                "teleNumb", userRegisteredEvent.getTeleNumb())).get(0).getFirstName() +
                "\nLastName: " + userClient.read(Map.of("email", userRegisteredEvent.getEmail(),
                "teleNumb", userRegisteredEvent.getTeleNumb())).get(0).getLastName() +
                "\nPhone number: " + userRegisteredEvent.getTeleNumb());
    }

    @KafkaListener(topics = "user-delete", groupId = "user")
    public void listen(@Payload UserDeletedEvent userDeletedEvent) {
        log.info("User of email: " + userDeletedEvent.getEmail() + " has been deleted" +
                "\nDetails: " +
                "\nFirst name: " + userClient.read(Map.of("email", userDeletedEvent.getEmail(),
                "teleNumb", userDeletedEvent.getTeleNumb())).get(0).getFirstName() +
                "\nLast name: " + userClient.read(Map.of("email", userDeletedEvent.getEmail(),
                "teleNumb", userDeletedEvent.getTeleNumb())).get(0).getLastName() +
                "\nPhone number: " + userDeletedEvent.getTeleNumb());
    }

    @KafkaListener(topics = "position-buy", groupId = "position")
    public void listen(@Payload PositionBoughtEvent positionBoughtEvent) {
        log.info("Position has been added to the portfolio of ID: " + positionBoughtEvent.getInvestingAccountID() +
                "\nPosition details: " +
                "\nCompany name: " + companyClient.read(Map.of("companyID",
                String.valueOf(positionBoughtEvent.getCompanyID()))).get(0).getCompanyName() +
                "\nTicker: " + positionBoughtEvent.getTicker() +
                "\nSize: " + positionBoughtEvent.getSize());
    }

    @KafkaListener(topics = "position-sell", groupId = "position")
    public void listen(@Payload PositionSoldEvent positionSoldEvent) {
        log.info("Position has been sold from portfolio of ID: " + positionSoldEvent.getInvestingAccountID() +
                "\nPosition details: " +
                "\nCompany name: " + companyClient.read(Map.of("companyID",
                String.valueOf(positionSoldEvent.getCompanyID()))).get(0).getCompanyName() +
                "\nTicker: " + positionSoldEvent.getTicker() +
                "\nSize: " + positionSoldEvent.getSize()
        );
    }
}
