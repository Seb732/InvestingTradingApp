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
        log.info("""
                   User of email: %s has been created
                   Details:\040
                   First name: %s
                   LastName: %s
                   Phone number: %s
                """.formatted(userRegisteredEvent.getEmail(),
                userClient.read(Map.of("email", userRegisteredEvent.getEmail(), "teleNumb", userRegisteredEvent.getTeleNumb())).get(0).getFirstName(),
                userClient.read(Map.of("email", userRegisteredEvent.getEmail(), "teleNumb", userRegisteredEvent.getTeleNumb())).get(0).getLastName(),
                userRegisteredEvent.getTeleNumb()));
    }

    @KafkaListener(topics = "user-delete", groupId = "user")
    public void listen(@Payload UserDeletedEvent userDeletedEvent) {
        log.info("""
                   User of email: %s has been deleted
                   Details:
                   First name: %s
                   Last name: %s
                   Phone number: %s
                """
                .formatted(userDeletedEvent.getEmail(), userClient.read(Map.of("email", userDeletedEvent.getEmail(),
                        "teleNumb", userDeletedEvent.getTeleNumb())).get(0).getFirstName(), userClient.read(Map.of("email", userDeletedEvent.getEmail(),
                        "teleNumb", userDeletedEvent.getTeleNumb())).get(0).getLastName(), userDeletedEvent.getTeleNumb()));
    }

    @KafkaListener(topics = "position-buy", groupId = "position")
    public void listen(@Payload PositionBoughtEvent positionBoughtEvent) {
        log.info("""
                Position has been added to the portfolio of ID: %d
                Position details:
                Company name: %s
                Ticker: %s
                Size: %d
                """
                .formatted(positionBoughtEvent.getInvestingAccountID(), companyClient.read(Map.of("companyID",
                        String.valueOf(positionBoughtEvent.getCompanyID()))).get(0).getCompanyName(), positionBoughtEvent.getTicker(), positionBoughtEvent.getSize()));
    }

    @KafkaListener(topics = "position-sell", groupId = "position")
    public void listen(@Payload PositionSoldEvent positionSoldEvent) {
        log.info("""
                Position has been sold from portfolio of ID: %d
                Position details:
                Company name: %s
                Ticker: %s
                Size: %d
                """
                .formatted(positionSoldEvent.getInvestingAccountID(), companyClient.read(Map.of("companyID",
                        String.valueOf(positionSoldEvent.getCompanyID()))).get(0).getCompanyName(), positionSoldEvent.getTicker(), positionSoldEvent.getSize()));
    }
}
