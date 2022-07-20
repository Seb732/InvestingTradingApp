package com.capgemini.investingtradingappuserclient.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionBoughtEvent {

    private long investingAccountID;

    private long companyID;

    private int size;

    private double ticker;
}
