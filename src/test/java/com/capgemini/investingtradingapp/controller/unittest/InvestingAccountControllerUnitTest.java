package com.capgemini.investingtradingapp.controller.unittest;

import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.entity.PositionStatus;
import com.capgemini.investingtradingapp.repository.PositionRepository;
import com.capgemini.investingtradingapp.service.InvestingAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InvestingAccountControllerUnitTest {

    static long investingAccountID;
    @MockBean
    private InvestingAccountService investingAccountService;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeAll
    static void setUp() {
        investingAccountID = 43;
    }

    @Test
    public void post_buyPosition_success() throws Exception {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(5);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);

        when(investingAccountService.buyPosition(investingAccountID, positionDTO)).thenReturn(modelMapper.map(positionDTO, Position.class));
        String json = objectMapper.writeValueAsString(positionDTO);
        mockMvc.perform(post("/user/investingAccount/" + investingAccountID + "/position")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(investingAccountService).buyPosition(investingAccountID, positionDTO);
    }

    @Test
    public void put_sellPosition_success() throws Exception {
        long positionID = 43;
        mockMvc.perform(put("/user/investingAccount/" + investingAccountID + "/position/" + positionID))
                .andExpect(status().isNoContent());
        verify(investingAccountService).sellPosition(investingAccountID, positionID);
    }

    @Test
    public void put_transferOUT_success() throws Exception {
        double amount = 999;
        mockMvc.perform(put("/user/investingAccount/" + investingAccountID + "/balance?amount=" + amount))
                .andExpect(status().isNoContent());
        verify(investingAccountService).transferOUT(investingAccountID, amount);
    }

}
