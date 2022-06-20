package com.capgemini.investingtradingapp.service;


import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@AllArgsConstructor
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Iterable<Position> getAll(){
        return positionRepository.findAll();
    }
    public Position createPosition(Position position){
        return positionRepository.save(position);
    }


}
