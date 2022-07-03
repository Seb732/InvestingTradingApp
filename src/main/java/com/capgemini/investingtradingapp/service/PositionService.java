package com.capgemini.investingtradingapp.service;


import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.entity.PositionStatus;
import com.capgemini.investingtradingapp.repository.InvestingAccountRepository;
import com.capgemini.investingtradingapp.repository.PositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save(long investingAccountID, PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        position.setInvestingAccount(investingAccountRepository.findById(investingAccountID).get());
        positionRepository.save(position);
    }

    public PositionDTO getByCompanyID(long id) {
        return modelMapper.map(positionRepository.findPositionByCompanyID(id), PositionDTO.class);
    }

    public List<PositionDTO> getByOpenDateAfter(LocalDateTime openDate) {
        return mapAll(positionRepository.findPositionByOpenDateAfter(openDate));
    }

    public List<PositionDTO> getPositionByTickerGreaterThan(double ticker) {
        return mapAll(positionRepository.findPositionByTickerGreaterThan(ticker));
    }

    public List<PositionDTO> getAll() {
        return mapAll(positionRepository.findAll());
    }

    public void update(long id, PositionDTO positionDTO) {
        Position position = positionRepository.findById(id).get();
        Position position1 = modelMapper.map(positionDTO, Position.class);

        if (position1.getPositionStatus() == PositionStatus.CLOSED) {
            position.setPositionStatus(position1.getPositionStatus());
            position.setCloseDate(LocalDateTime.now());
        }

        if (position1.getCloseDate() != null) {
            position.setCloseDate(position1.getCloseDate());
        }
    }

    public void delete(long positionID) {
        positionRepository.deleteById(positionID);
    }

    private List<PositionDTO> mapAll(List<Position> positions) {
        return positions.stream().map(position -> modelMapper.map(position, PositionDTO.class)).toList();
    }


}
