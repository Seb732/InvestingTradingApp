package com.capgemini.investingtradingappuser.service;

import com.capgemini.investingtradingappuser.entity.InvestingAccount;
import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.entity.PositionStatus;
import com.capgemini.investingtradingappuser.repository.InvestingAccountRepository;
import com.capgemini.investingtradingappposition.repository.PositionRepository;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.exception.PositionNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = "com.capgemini.investingtradingappposition")
public class InvestingAccountService {
    @Autowired
    private InvestingAccountRepository investingAccountRepository;

//    @Autowired
//    private PositionRepository positionRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;

//    public Position buyPosition(long investingAccountID, PositionDTO positionDTO) throws com.capgemini.investingtradingappposition.exception.InsufficientFoundsException {
//        Position position = modelMapper.map(positionDTO, Position.class);
//        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
//        investingAccount.buy(position.getCompanyID(), position.getSize(), position.getTicker(), investingAccount);
//
//        investingAccountRepository.save(investingAccount);
//        return position;
//    }

//    public void sellPosition(long investingAccountID, long positionID) throws PositionNotFoundException {
//        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
//        investingAccount.sell(positionRepository.findById(positionID).get());
//        Position position = positionRepository.findById(positionID).get();
//        position.setPositionStatus(PositionStatus.CLOSED);
//        position.setCloseDate(LocalDateTime.now());
//        investingAccountRepository.save(investingAccount);
//        positionRepository.save(position);
//    }

    public void transferOUT(long investingAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException {
        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        investingAccount.getUser().transferOut(amount);
        investingAccountRepository.save(investingAccount);
    }


    public List<InvestingAccount> getAll() {
        return investingAccountRepository.findAll();
    }

}
