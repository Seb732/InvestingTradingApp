package com.capgemini.investingtradingappposition.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "companies", indexes = {
        @Index(name = "company_name_index", columnList = "company_name"),
        @Index(name = "ticker_symbol_index", columnList = "ticker_symbol")
})
/**
 * This class represents a company which shares user buys.
 */
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyID;

    /**
     * Company's full name abbreviation eg. Apple Inc. - AAPL
     */
    @Column(name = "ticker_symbol", nullable = false)
    private String tickerSymbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;


}
