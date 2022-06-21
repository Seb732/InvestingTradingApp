package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.repository.InvestingAccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class InvestingAccountService {
    @Autowired
    private InvestingAccountRepository investingAccountRepository;

    public Iterable<InvestingAccount> getAll(){
        return investingAccountRepository.findAll();
    }

}
