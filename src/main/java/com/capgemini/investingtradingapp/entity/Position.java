package com.capgemini.investingtradingapp.entity;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor

@ToString
@Getter
@Setter

/**
 * This class represents single stock in user's portfolio.
 */
@Entity
@Table(name = "positions")
public class Position {
    /**
     * ticker - current stock price, size - number of bought shares
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id", nullable = false)
    private long positionID;

    @Column(name = "company_id", nullable = false)
    private String companyID;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "ticker", nullable = false)
    private double ticker;

    @ManyToOne()
    @JoinColumn(name = "investing_account_id", nullable = false)
    private InvestingAccount investingAccount;

    //TODO need to add timestamp for time-tracking

    public Position(String companyID, int size, double ticker){
        this.setCompanyID(companyID);
        this.setSize(size);
        this.setTicker(ticker);
    }
}
