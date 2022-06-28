package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InvestingAccountRepository extends JpaRepository<InvestingAccount, Long> {

    Optional<InvestingAccount> findInvestingAccountByUser(User user);
}