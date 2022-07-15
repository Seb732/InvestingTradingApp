package controller.integrationtest;


import com.capgemini.investingtradingappposition.entity.Company;
import com.capgemini.investingtradingappposition.service.CompanyService;
import com.capgemini.investingtradingapppositionclient.dto.CompanyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.capgemini")
public class CompanyControllerIntegrationTest {

    @Autowired
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
        String json = objectMapper.writeValueAsString(companyDto);
        mockMvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_AllCompanies_success() throws Exception {
        mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void get_CompanyByName_success() throws Exception {
        mockMvc.perform(get("/company?companyName=Tesla Inc."))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(companyService.getByCompanyName("Tesla Inc."))));
    }

    @Test
    public void get_CompanyByTickerSymbol_success() throws Exception {
        mockMvc.perform(get("/company?tickerSymbol=TSLA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(companyService.getByTickerSymbol("TSLA"))));
    }

    @Test
    public void put_Company_success() throws Exception {

        CompanyDto companyDto = new CompanyDto("Abcd Inc.", "ABCD");
        Company company = companyService.save(companyDto);


        String json = objectMapper.writeValueAsString(companyDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/company/" + company.getCompanyID())
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_Company_success() throws Exception {
        Company company = companyService.save(new CompanyDto("Abcde Inc.", "ABCDE"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/company?companyID=" + company.getCompanyID())
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }

}