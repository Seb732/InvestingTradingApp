package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findCompanyByCompanyName(String companyName);

    List<Company> findCompanyByTickerSymbol(String tickerSymbol);
}
