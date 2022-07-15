package com.capgemini.investingtradingapppositionclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Dto class for company
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotBlank(message = "Ticker symbol is mandatory")
    private String tickerSymbol;

}
