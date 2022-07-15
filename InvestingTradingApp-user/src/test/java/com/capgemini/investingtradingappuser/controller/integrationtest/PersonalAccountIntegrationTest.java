package com.capgemini.investingtradingappuser.controller.integrationtest;

import com.capgemini.investingtradingappuser.entity.User;
import com.capgemini.investingtradingappuser.service.PersonalAccountService;
import com.capgemini.investingtradingappuser.service.UserService;
import com.capgemini.investingtradingappuserclient.dto.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonalAccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonalAccountService personalAccountService;

    @Autowired
    private UserService userService;


    @Test
    public void put_deposit_success() throws Exception {
        double amount = 999;
        String firstName = "Jeff";
        String lastName = "Smith";
        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));

        mockMvc.perform(put("/user/personalAccount/" + user.getPersonalAccount().getPersonalAccountID() + "/balance?operationType=deposit&amount=" + amount))
                .andExpect(status().isNoContent());
    }

    @Test
    public void put_withdrawal_success() throws Exception {
        double amount = 999;
        String firstName = "Jeff";
        String lastName = "Smith";
        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
        personalAccountService.deposit(user.getPersonalAccount().getPersonalAccountID(), amount);
        mockMvc.perform(put("/user/personalAccount/" + user.getPersonalAccount().getPersonalAccountID() + "/balance?operationType=withdrawal&amount=" + amount))
                .andExpect(status().isNoContent());
    }

    @Test
    public void put_transferIN_success() throws Exception {
        double amount = 999;
        String firstName = "Jeff";
        String lastName = "Smith";
        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
        personalAccountService.deposit(user.getPersonalAccount().getPersonalAccountID(), amount);
        mockMvc.perform(put("/user/personalAccount/" + user.getPersonalAccount().getPersonalAccountID() + "/balance?operationType=transferIN&amount=" + amount))
                .andExpect(status().isNoContent());
    }
}
