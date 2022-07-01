package com.capgemini.investingtradingapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "company_id")
    private long companyID;

    @Column(name = "ticker_symbol", nullable = false)
    private String tickerSymbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;


}
