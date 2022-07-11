package com.capgemini.investingtradingapp.controller.unittest;

import com.capgemini.investingtradingapp.dto.CompanyDto;
import com.capgemini.investingtradingapp.entity.Company;
import com.capgemini.investingtradingapp.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerUnitTest {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void post_Company_success() throws Exception {
        Company company = new Company();
        company.setCompanyName("Tesla Inc.");
        company.setTickerSymbol("TSLA");
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        when(companyService.save(any(CompanyDto.class))).thenReturn(company);
        String json = objectMapper.writeValueAsString(companyDto);
        mockMvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(companyService).save(companyDto);
    }

    @Test
    public void get_AllCompanies_success() throws Exception {
        mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(companyService).getAll();
    }

    @Test
    public void get_CompanyByName_success() throws Exception {
        mockMvc.perform(get("/company?companyName=Tesla Inc."))
                .andExpect(status().isOk());
        verify(companyService).getByCompanyName("Tesla Inc.");
    }

    @Test
    public void get_CompanyByTickerSymbol_success() throws Exception {
        mockMvc.perform(get("/company?tickerSymbol=TSLA"))
                .andExpect(status().isOk());
        verify(companyService).getByTickerSymbol("TSLA");
    }

    @Test
    public void put_Company_success() throws Exception {
        Company company = new Company();
        company.setCompanyName("Tesla Inc.");
        company.setTickerSymbol("TSLA");
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        when(companyService.update(4, companyDto)).thenReturn(company);
        String json = objectMapper.writeValueAsString(companyDto);
        mockMvc.perform(put("/company/4").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
        verify(companyService).update(4, companyDto);
    }

    @Test
    public void delete_Company_success() throws Exception {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName("Tesla Inc.");
        companyDto.setTickerSymbol("TSLA");
        mockMvc.perform(delete("/company?companyID=4").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }

}