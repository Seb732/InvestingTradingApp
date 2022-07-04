package com.capgemini.investingtradingapp.repository;


import com.capgemini.investingtradingapp.entity.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {

}
