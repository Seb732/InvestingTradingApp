package com.capgemini.investingtradingapp.dto;

import com.capgemini.investingtradingapp.entity.PositionStatus;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Data
public class PositionDTO {

    @PositiveOrZero
    private long companyID;

    @Min(value = 1)
    private int size;

    @Positive
    private double ticker;

    private PositionStatus positionStatus;

    @PastOrPresent
    private LocalDateTime openDate;

    private LocalDateTime closeDate;


}
