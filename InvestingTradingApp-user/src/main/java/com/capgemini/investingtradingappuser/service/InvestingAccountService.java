package com.capgemini.investingtradingappuser.service;

import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.entity.PositionStatus;
import com.capgemini.investingtradingappposition.repository.PositionRepository;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import com.capgemini.investingtradingappuser.entity.InvestingAccount;
import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.repository.InvestingAccountRepository;
import com.capgemini.investingtradingappuserclient.event.PositionBoughtEvent;
import com.capgemini.investingtradingappuserclient.event.PositionSoldEvent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = "com.capgemini.investingtradingappposition")
public class InvestingAccountService {
    @Autowired
    private InvestingAccountRepository investingAccountRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Position buyPosition(PositionDTO positionDTO) throws com.capgemini.investingtradingappposition.exception.InsufficientFoundsException {
        Position position = modelMapper.map(positionDTO, Position.class);
        InvestingAccount investingAccount = investingAccountRepository.findById(position.getInvestingAccountID()).get();
        investingAccount.buy(position.getCompanyID(), position.getSize(), position.getTicker(), positionDTO.getInvestingAccountID());
        kafkaTemplate.send("position-buy", String.valueOf(investingAccount.getInvestingAccountID()),
                new PositionBoughtEvent(investingAccount.getInvestingAccountID(), position.getCompanyID(), position.getTicker(), position.getSize()));
        investingAccountRepository.save(investingAccount);
        positionRepository.save(position);
        return position;
    }

    public void sellPosition(long positionID) throws com.capgemini.investingtradingappuser.exception.PositionNotFoundException {
        Position position = positionRepository.findById(positionID).get();
        InvestingAccount investingAccount = investingAccountRepository.findById(position.getInvestingAccountID()).get();
        investingAccount.sell(position);
        position.setPositionStatus(PositionStatus.CLOSED);
        position.setCloseDate(LocalDateTime.now());
        kafkaTemplate.send("position-sell", String.valueOf(investingAccount.getInvestingAccountID()),
                new PositionSoldEvent(positionID, investingAccount.getInvestingAccountID(), position.getCompanyID(), position.getTicker(), position.getSize()));
        investingAccountRepository.save(investingAccount);
        positionRepository.save(position);
    }

    public void transferOUT(long investingAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException {
        InvestingAccount investingAccount = investingAccountRepository.findById(investingAccountID).get();
        investingAccount.getUser().transferOut(amount);
        investingAccountRepository.save(investingAccount);
    }


    public List<InvestingAccount> getAll() {
        return investingAccountRepository.findAll();
    }

}
