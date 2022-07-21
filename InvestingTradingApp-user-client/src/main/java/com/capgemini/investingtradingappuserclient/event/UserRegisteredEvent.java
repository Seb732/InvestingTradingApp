package com.capgemini.investingtradingappuserclient.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisteredEvent {

    private String email;

    private String teleNumb;

}
