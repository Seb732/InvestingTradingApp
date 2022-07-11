package com.capgemini.investingtradingapp.repositories.tests;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.entity.PersonalAccount;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    User user;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init() throws IncorrectEmailException, IncorrectTeleNumbException {
        user = new User();
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setEmail("test@gmail.com");
        user.setTeleNumb("111-222-333");
        InvestingAccount investingAccount = new InvestingAccount();
        PersonalAccount personalAccount = new PersonalAccount();
        user.setInvestingAccount(investingAccount);
        user.getInvestingAccount().setUser(user);
        user.setPersonalAccount(personalAccount);
        user.getPersonalAccount().setUser(user);
        userRepository.save(user);
    }

    @Test
    void findUserByFirstNameAndLastNameSuccess(){
        List<User> users = userRepository.findUserByFirstNameAndLastName("testName", "testLastName");
        assertEquals(1,users.size());
        assertEquals(user.getUserID(),users.get(0).getUserID());
    }

    @Test
    void findUserByTeleNumbAndEmail(){
        List<User> users = userRepository.findUserByTeleNumbAndEmail("111-222-333", "test@gmail.com");
        assertEquals(1,users.size());
        assertEquals(user.getUserID(),users.get(0).getUserID());
    }
}
