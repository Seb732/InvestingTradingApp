package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findPositionByCompanyID(long positionID);

    List<Position> findPositionByOpenDateAfter(LocalDateTime openDate);

    List<Position> findPositionByTickerGreaterThan(double ticker);

}