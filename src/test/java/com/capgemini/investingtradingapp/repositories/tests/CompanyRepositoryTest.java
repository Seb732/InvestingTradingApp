package com.capgemini.investingtradingapp.repositories.tests;

import com.capgemini.investingtradingapp.entity.Company;
import com.capgemini.investingtradingapp.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CompanyRepositoryTest {

    Company company;
    Company company2;

    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    void init(){
        company = new Company();
        company.setCompanyName("TestName");
        company.setTickerSymbol("TestSymbol");

        company2 = new Company();
        company2.setCompanyName("WrongName");
        company2.setTickerSymbol("WrongSymbol");

        companyRepository.save(company);
        companyRepository.save(company2);
    }

    @Test
    void findCompanyByCompanyNameSuccess(){
        List<Company> companies = companyRepository.findCompanyByCompanyName("TestName");
        assertEquals(1, companies.size());
        assertEquals(company.getTickerSymbol(), companies.get(0).getTickerSymbol());
        assertEquals(company.getCompanyID(), companies.get(0).getCompanyID());
    }

    @Test
    void findCompanyByTickerSymbolSuccess(){
        List<Company> companies = companyRepository.findCompanyByTickerSymbol("TestSymbol");
        assertEquals(1, companies.size());
        assertEquals(company.getCompanyID(), companies.get(0).getCompanyID());
        assertEquals(company.getCompanyName(), companies.get(0).getCompanyName());
    }

}
