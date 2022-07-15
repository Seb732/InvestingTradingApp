package com.capgemini.investingtradingappposition.service;


import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.entity.PositionStatus;
import com.capgemini.investingtradingappposition.exception.PositionNotFoundException;
import com.capgemini.investingtradingappposition.repository.PositionRepository;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = "com.capgemini.investingtradingappuser")
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

//    @Autowired
//    private InvestingAccountRepository investingAccountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @CachePut(value = "position", keyGenerator = "customKeyGenerator")
    public Position save(long investingAccountID, @Valid PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        //position.setInvestingAccount(investingAccountRepository.findById(investingAccountID).get());
        return positionRepository.save(position);
    }
    @CachePut(value = "position", keyGenerator = "customKeyGenerator")
    public Position buyPosition(long investingAccountID, PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        //InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        //investingAccount.buy(position.getCompanyID(), position.getSize(), position.getTicker(), investingAccount);

        //investingAccountRepository.save(investingAccount);
        return position;
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

    @CachePut(value = "position", keyGenerator = "customKeyGenerator")
    public void sellPosition(long investingAccountID, long positionID) throws PositionNotFoundException {
      //  InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        //investingAccount.sell(positionRepository.findById(positionID).get());
        Position position = positionRepository.findById(positionID).get();
        position.setPositionStatus(PositionStatus.CLOSED);
        position.setCloseDate(LocalDateTime.now());
        //investingAccountRepository.save(investingAccount);
        positionRepository.save(position);
    }

    @CacheEvict(value = "position", keyGenerator = "customKeyGenerator")
    public void delete(long positionID) {
        positionRepository.deleteById(positionID);
    }

    private List<PositionDTO> mapAll(List<Position> positions) {
        return positions.stream().map(position -> modelMapper.map(position, PositionDTO.class)).toList();
    }


}
