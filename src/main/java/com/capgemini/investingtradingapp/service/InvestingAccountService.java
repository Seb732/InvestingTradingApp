package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.entity.PositionStatus;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.PositionNotFoundException;
import com.capgemini.investingtradingapp.repository.InvestingAccountRepository;
import com.capgemini.investingtradingapp.repository.PositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvestingAccountService {
    @Autowired
    private InvestingAccountRepository investingAccountRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void buyPosition(long investingAccountID, PositionDTO positionDTO) throws InsufficientFoundsException {
        Position position = modelMapper.map(positionDTO, Position.class);
        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        investingAccount.buy(position.getCompanyID(), position.getSize(), position.getTicker(), investingAccount);

        investingAccountRepository.save(investingAccount);
    }

    public void sellPosition(long investingAccountID, long positionID) throws PositionNotFoundException {
        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        investingAccount.sell(positionRepository.findById(positionID).get());
        Position position = positionRepository.findById(positionID).get();
        position.setPositionStatus(PositionStatus.CLOSED);
        position.setCloseDate(LocalDateTime.now());
        positionRepository.save(position);
        investingAccountRepository.save(investingAccount);
    }

    public List<InvestingAccount> getAll() {
        return investingAccountRepository.findAll();
    }

}
