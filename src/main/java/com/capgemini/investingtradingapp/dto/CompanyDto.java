package com.capgemini.investingtradingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CompanyDto implements Serializable {

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotBlank(message = "Ticker symbol is mandatory")
    private String tickerSymbol;

}
