package com.capgemini.investingtradingapp.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

/**
 * This class represents single stock in user's portfolio.
 */
public class Position {
    /**
     * ticker - current stock price, size - number of bought shares
     */
    String companyID;
    int size;
    Double ticker;
}
