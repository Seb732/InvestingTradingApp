package com.capgemini.investingtradingapp.dto;

import com.capgemini.investingtradingapp.entity.PositionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * Dto class for position
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {

    @PositiveOrZero(message = "Company's id has to be positive non-negative")
    private long companyID;

    @Min(value = 1, message = "Position size has to be greater or equal to one")
    private int size;

    @Positive(message = "Ticker value has to be positive")
    private double ticker;

    private PositionStatus positionStatus;

    @PastOrPresent(message = "Open date has to be a past-time date")
    private LocalDateTime openDate;

    private LocalDateTime closeDate;


}
