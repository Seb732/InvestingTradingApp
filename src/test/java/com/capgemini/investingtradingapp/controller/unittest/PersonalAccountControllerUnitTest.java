package com.capgemini.investingtradingapp.controller.unittest;

import com.capgemini.investingtradingapp.repository.PersonalAccountRepository;
import com.capgemini.investingtradingapp.service.PersonalAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonalAccountControllerUnitTest {

    static long personalAccountID;
    static double amount;
    @MockBean
    private PersonalAccountService personalAccountService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @BeforeAll
    private static void setUp() {
        personalAccountID = 15;
        amount = 300;
    }

    @Test
    public void put_deposit_success() throws Exception {
        mockMvc.perform(put("/user/personalAccount/" + personalAccountID + "/balance?operationType=deposit&amount=" + amount))
                .andExpect(status().isNoContent());
    }

    @Test
    public void put_withdrawal_success() throws Exception {
        mockMvc.perform(put("/user/personalAccount/" + personalAccountID + "/balance?operationType=withdrawal&amount=" + amount))
                .andExpect(status().isNoContent());
    }

    @Test
    public void put_transferIN_success() throws Exception {
        mockMvc.perform(put("/user/personalAccount/" + personalAccountID + "/balance?operationType=transferIN&amount=" + amount))
                .andExpect(status().isNoContent());
    }
}
