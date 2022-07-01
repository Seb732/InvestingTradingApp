package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByCompanyName(String companyName);

    Company findCompanyByTickerSymbol(String tickerSymbol);
}
