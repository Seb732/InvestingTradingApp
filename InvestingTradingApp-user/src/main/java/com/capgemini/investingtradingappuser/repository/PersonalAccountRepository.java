package com.capgemini.investingtradingappuser.repository;


import com.capgemini.investingtradingappuser.entity.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for user's personal account
 */
public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {

}
