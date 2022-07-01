package com.capgemini.investingtradingapp.dto;

import com.capgemini.investingtradingapp.entity.PositionStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PositionDTO {
    private long companyID;

    private int size;

    private double ticker;

    private PositionStatus positionStatus;

    private LocalDateTime openDate;

    private LocalDateTime closeDate;


}
