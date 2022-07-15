package com.capgemini.investingtradingappposition.service;


import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.entity.PositionStatus;
import com.capgemini.investingtradingappposition.repository.PositionRepository;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CachePut(value = "position", keyGenerator = "customKeyGen")
    public Position save(PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        return positionRepository.save(position);
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGen")
    public List<PositionDTO> getByCompanyID(long companyID) {
        return mapAll(positionRepository.findPositionByCompanyID(companyID));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGen")
    public List<PositionDTO> getByOpenDateAfter(LocalDateTime openDate) {
        return mapAll(positionRepository.findPositionByOpenDateAfter(openDate));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGen")
    public List<PositionDTO> getPositionByTickerGreaterThan(double ticker) {
        return mapAll(positionRepository.findPositionByTickerGreaterThan(ticker));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGen")
    public List<PositionDTO> getAll() {
        return mapAll(positionRepository.findAll());
    }

    @CachePut(value = "position", keyGenerator = "customKeyGen")
    public Position update(long positionID, PositionDTO positionDTO) {
        Position position = positionRepository.findById(positionID).get();
        Position position1 = modelMapper.map(positionDTO, Position.class);

        if (position1.getPositionStatus() == PositionStatus.CLOSED) {
            position.setPositionStatus(position1.getPositionStatus());
            position.setCloseDate(LocalDateTime.now());
        }

        if (position1.getCloseDate() != null) {
            position.setCloseDate(position1.getCloseDate());
        }
        return positionRepository.save(position);
    }

    @CacheEvict(value = "position", keyGenerator = "customKeyGen")
    public void delete(long positionID) {
        positionRepository.deleteById(positionID);
    }

    private List<PositionDTO> mapAll(List<Position> positions) {
        return positions.stream().map(position -> modelMapper.map(position, PositionDTO.class)).toList();
    }


}
