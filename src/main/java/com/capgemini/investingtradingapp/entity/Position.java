package com.capgemini.investingtradingapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "position_id")
    private long positionID;

    @Column(name = "company_id", nullable = false)
    private long companyID;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "ticker", nullable = false)
    private double ticker;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_status", nullable = false)
    private PositionStatus positionStatus = PositionStatus.OPEN;

    @CreationTimestamp
    @Column(name = "open_date", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "close_date")
    private LocalDateTime closeDate;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "investing_account_id", referencedColumnName = "investing_account_id", nullable = false)
    private InvestingAccount investingAccount;


}
