package com.capgemini.investingtradingappuser.repository;

import com.capgemini.investingtradingappuser.entity.InvestingAccount;
import com.capgemini.investingtradingappuser.entity.PersonalAccount;
import com.capgemini.investingtradingappuser.entity.User;
import com.capgemini.investingtradingappuser.exception.IncorrectEmailException;
import com.capgemini.investingtradingappuser.exception.IncorrectTeleNumbException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

     //   InvestingAccount testedInvestingAccount = investingAccountRepository.findInvestingAccountByUser(user);
//        assertEquals(investingAccount.getInvestingAccountID(), testedInvestingAccount.getInvestingAccountID());
//        assertEquals(investingAccount.getBalance(), testedInvestingAccount.getBalance());
//        assertEquals(user, testedInvestingAccount.getUser());


    }
}
