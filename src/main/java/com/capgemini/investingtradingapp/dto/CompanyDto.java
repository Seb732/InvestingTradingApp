package com.capgemini.investingtradingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Dto class for company
 */
@Data
public class CompanyDto {

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotBlank(message = "Ticker symbol is mandatory")
    private String tickerSymbol;

}
