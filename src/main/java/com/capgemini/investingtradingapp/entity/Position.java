package com.capgemini.investingtradingapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor

@ToString
@Getter
@Setter

/**
 * This class represents single stock in user's portfolio.
 */
@Entity
@Table(name = "positions", indexes = {
        @Index(name = "company_id_index", columnList = "company_id"),
        @Index(name = "ticker_index", columnList = "ticker"),
        @Index(name = "open_date_index", columnList = "open_date")
})
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
