package com.capgemini.investingtradingapp.entity;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@NoArgsConstructor

@ToString
@Getter
@Setter

/**
 * This class represents single stock in user's portfolio.
 */
@Entity
@Table(name = "position")
public class Position {
    /**
     * ticker - current stock price, size - number of bought shares
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "positionid", nullable = false)
    long positionID;

    @Column(name = "companyid", nullable = false)
    String companyID;
    @Column(name = "size", nullable = false)
    int size;
    @Column(name = "ticker", nullable = false)
    double ticker;
    @Column(name = "userid", nullable = false)
    long userID;

    public Position(String companyID, int size, double ticker){
        this.setCompanyID(companyID);
        this.setSize(size);
        this.setTicker(ticker);
    }
}
