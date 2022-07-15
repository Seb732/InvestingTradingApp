package com.capgemini.investingtradingappposition.repository;

import com.capgemini.investingtradingappposition.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Company's class repository
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Method for finding the company by its name.
     *
     * @param companyName
     * @return - company with given name
     */
    List<Company> findCompanyByCompanyName(String companyName);

    /**
     * Method for finding the company by ticker symbol.
     *
     * @param tickerSymbol
     * @return - company with given ticker symbol
     */
    List<Company> findCompanyByTickerSymbol(String tickerSymbol);
}
