package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestingAccountRepository extends JpaRepository<InvestingAccount, Long> {
}