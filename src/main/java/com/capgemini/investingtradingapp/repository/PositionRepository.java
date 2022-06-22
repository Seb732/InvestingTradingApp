package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findPositionByCompanyID(String companyID);
    Optional<Position> findPositionByOpenDateAfter(LocalDateTime openDate);
    Optional<Position> findPositionByTickerGreaterThan(double ticker);
}