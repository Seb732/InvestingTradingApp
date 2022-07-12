package com.capgemini.investingtradingapp.controller.integrationtest;

import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.entity.PositionStatus;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.service.PersonalAccountService;
import com.capgemini.investingtradingapp.service.PositionService;
import com.capgemini.investingtradingapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InvestingAccountControllerIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;

    @Autowired
    private PersonalAccountService personalAccountService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void post_buyPosition_success() throws Exception {
        String firstName = "Jeff";
        String lastName = "Smith";

        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
        personalAccountService.deposit(user.getPersonalAccount().getPersonalAccountID(), 10000);
        personalAccountService.transferIN(user.getPersonalAccount().getPersonalAccountID(), 5000);

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(5);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);
        positionService.save(user.getInvestingAccount().getInvestingAccountID(), positionDTO);
        String json = objectMapper.writeValueAsString(positionDTO);
        mockMvc.perform(post("/user/investingAccount/" + user.getInvestingAccount().getInvestingAccountID() + "/position")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void put_sellPosition_success() throws Exception {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(5);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);

        String firstName = "Jeff";
        String lastName = "Smith";


        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
        Position position = positionService.save(user.getInvestingAccount().getInvestingAccountID(), positionDTO);
        mockMvc.perform(put("/user/investingAccount/" + user.getInvestingAccount().getInvestingAccountID() + "/position/" + position.getPositionID()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void put_transferOUT_success() throws Exception {
        String firstName = "Jeff";
        String lastName = "Smith";
        double amount = 999;

        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
        personalAccountService.deposit(user.getPersonalAccount().getPersonalAccountID(), 10000);
        personalAccountService.transferIN(user.getPersonalAccount().getPersonalAccountID(), 5000);

        mockMvc.perform(put("/user/investingAccount/" + user.getInvestingAccount().getInvestingAccountID() + "/balance?amount=" + amount))
                .andExpect(status().isNoContent());
    }
}
