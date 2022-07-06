package com.capgemini.investingtradingapp.service;


import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.entity.PositionStatus;
import com.capgemini.investingtradingapp.repository.InvestingAccountRepository;
import com.capgemini.investingtradingapp.repository.PositionRepository;
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
    private InvestingAccountRepository investingAccountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @CachePut(value = "position", keyGenerator = "customKeyGenerator")
    public void save(long investingAccountID, PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        position.setInvestingAccount(investingAccountRepository.findById(investingAccountID).get());
        positionRepository.save(position);
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGenerator")
    public List<PositionDTO> getByCompanyID(long companyID) {
        return mapAll(positionRepository.findPositionByCompanyID(companyID));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGenerator")
    public List<PositionDTO> getByOpenDateAfter(LocalDateTime openDate) {
        return mapAll(positionRepository.findPositionByOpenDateAfter(openDate));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGenerator")
    public List<PositionDTO> getPositionByTickerGreaterThan(double ticker) {
        return mapAll(positionRepository.findPositionByTickerGreaterThan(ticker));
    }

    @Cacheable(value = "position", keyGenerator = "customKeyGenerator")
    public List<PositionDTO> getAll() {
        return mapAll(positionRepository.findAll());
    }

    @CachePut(value = "position", keyGenerator = "customKeyGenerator")
    public void update(long positionID, PositionDTO positionDTO) {
        Position position = positionRepository.findById(positionID).get();
        Position position1 = modelMapper.map(positionDTO, Position.class);

        if (position1.getPositionStatus() == PositionStatus.CLOSED) {
            position.setPositionStatus(position1.getPositionStatus());
            position.setCloseDate(LocalDateTime.now());
        }

        if (position1.getCloseDate() != null) {
            position.setCloseDate(position1.getCloseDate());
        }
    }

    @CacheEvict(value = "position", keyGenerator = "customKeyGenerator")
    public void delete(long positionID) {
        positionRepository.deleteById(positionID);
    }

    private List<PositionDTO> mapAll(List<Position> positions) {
        return positions.stream().map(position -> modelMapper.map(position, PositionDTO.class)).toList();
    }


}
