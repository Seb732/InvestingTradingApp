package com.capgemini.investingtradingappnotification.config;

import com.capgemini.investingtradingappuserclient.event.PositionBoughtEvent;
import com.capgemini.investingtradingappuserclient.event.PositionSoldEvent;
import com.capgemini.investingtradingappuserclient.event.UserDeletedEvent;
import com.capgemini.investingtradingappuserclient.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @KafkaListener(topics = "user-create", groupId = "user-creation")
    public void listen(@Payload UserRegisteredEvent userRegisteredEvent) {
        log.info("User of email: " + userRegisteredEvent.getEmail() + " has been created");
    }

    @KafkaListener(topics = "user-delete", groupId = "user-deletion")
    public void listen(@Payload UserDeletedEvent userDeletedEvent) {
        log.info("User of email: " + userDeletedEvent.getEmail() + " has been deleted");
    }

    @KafkaListener(topics = "position-buy", groupId = "position-buyinh")
    public void listen(@Payload PositionBoughtEvent positionBoughtEvent) {
        log.info("Position has been added to the portfolio of ID: " + positionBoughtEvent.getInvestingAccountID());
    }

    @KafkaListener(topics = "position-sell", groupId = "position-selling")
    public void listen(@Payload PositionSoldEvent positionSoldEvent) {
        log.info("Position has been sold from portfolio of ID: " + positionSoldEvent.getInvestingAccountID());
    }
}
