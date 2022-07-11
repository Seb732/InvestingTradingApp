package com.capgemini.investingtradingapp.repositories.tests;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.entity.PersonalAccount;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.repository.InvestingAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InvestingAccountRepositoryTest {

    @Autowired
    InvestingAccountRepository investingAccountRepository;

    @Test
    void findInvestingAccountByUserSuccess() throws IncorrectEmailException, IncorrectTeleNumbException {
        InvestingAccount investingAccount = new InvestingAccount();
        PersonalAccount personalAccount = new PersonalAccount();
        User user = new User();
        user.setFirstName("testName");
        user.setLastName("testLastname");
        user.setTeleNumb("111-222-333");
        user.setEmail("test@gmail.com");
        user.setInvestingAccount(investingAccount);
        user.setPersonalAccount(personalAccount);
        user.getPersonalAccount().setUser(user);
        user.getInvestingAccount().setUser(user);
        investingAccount.setBalance(100);
        investingAccountRepository.save(investingAccount);

        InvestingAccount testedInvestingAccount = investingAccountRepository.findInvestingAccountByUser(user);
        assertEquals(investingAccount.getInvestingAccountID(),testedInvestingAccount.getInvestingAccountID());
        assertEquals(investingAccount.getBalance(),testedInvestingAccount.getBalance());
        assertEquals(user, testedInvestingAccount.getUser());



    }
}
