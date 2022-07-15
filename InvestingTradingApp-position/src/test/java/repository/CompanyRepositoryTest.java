package repository;

import com.capgemini.investingtradingappposition.entity.Company;
import com.capgemini.investingtradingappposition.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest()
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
        Assertions.assertEquals(company.getTickerSymbol(), companies.get(0).getTickerSymbol());
        Assertions.assertEquals(company.getCompanyID(), companies.get(0).getCompanyID());
    }

    @Test
    void findCompanyByTickerSymbolSuccess(){
        List<Company> companies = companyRepository.findCompanyByTickerSymbol("TestSymbol");
        assertEquals(1, companies.size());
        Assertions.assertEquals(company.getCompanyID(), companies.get(0).getCompanyID());
        Assertions.assertEquals(company.getCompanyName(), companies.get(0).getCompanyName());
    }

}
