package com.capgemini.investingtradingapp.service;


import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.repository.PositionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PositionService {

    private PositionRepository positionRepository;

    public Iterable<Position> getAll() {
        return positionRepository.findAll();
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }


}
