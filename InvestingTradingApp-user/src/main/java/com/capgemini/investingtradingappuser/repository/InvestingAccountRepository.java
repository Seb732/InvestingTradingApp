package com.capgemini.investingtradingappuser.repository;

import com.capgemini.investingtradingappuser.entity.InvestingAccount;
import com.capgemini.investingtradingappuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for user's investing account
 */
public interface InvestingAccountRepository extends JpaRepository<InvestingAccount, Long> {
    /**
     * Method for finding the investing account by User
     *
     * @param user
     * @return - Given user's investing account
     */
    List<InvestingAccount> findInvestingAccountByUser(User user);
}