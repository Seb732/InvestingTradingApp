package com.capgemini.investingtradingappposition.repository;

import com.capgemini.investingtradingappposition.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Position class repository
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    /**
     * This method finds positions by company id
     *
     * @param positionID
     * @return - positions with given company id
     */
    List<Position> findPositionByCompanyID(long positionID);

    /**
     * This method finds positions which was opened after given date
     *
     * @param openDate
     * @return - positions with open date later than given
     */
    List<Position> findPositionByOpenDateAfter(LocalDateTime openDate);

    /**
     * Method for finding positions with ticker greater than given
     *
     * @param ticker
     * @return - positions with ticker greater than given
     */
    List<Position> findPositionByTickerGreaterThan(double ticker);

}