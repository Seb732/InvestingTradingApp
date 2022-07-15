package com.capgemini.investingtradingappposition.service;

import com.capgemini.investingtradingappposition.entity.Company;
import com.capgemini.investingtradingappposition.repository.CompanyRepository;
import com.capgemini.investingtradingapppositionclient.dto.CompanyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Company's class service
 */
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * This method saves given company record to database
     *
     * @param companyDto - company dto to be mapped to company class
     */
    @CachePut(value = "companies", keyGenerator = "customKeyGenerator")
    public Company save(CompanyDto companyDto) {
        return companyRepository.save(modelMapper.map(companyDto, Company.class));
    }

    /**
     * This method finds the all companies records
     *
     * @return - list of all companies
     */
    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getAll() {
        return mapAll(companyRepository.findAll());
    }

    /**
     * This method finds company by name
     *
     * @param name
     * @return - company
     */
    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getByCompanyName(String name) {
        return mapAll(companyRepository.findCompanyByCompanyName(name));
    }

    /**
     * This method finds company by ticker symbol
     *
     * @param tickerSymbol
     * @return - company with given ticker symbol's dto
     */
    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getByTickerSymbol(String tickerSymbol) {
        return mapAll(companyRepository.findCompanyByTickerSymbol(tickerSymbol));
    }

    @CachePut(value = "companies", keyGenerator = "customKeyGenerator")
    public Company update(long companyID, CompanyDto companyDto) {
        Company company = companyRepository.findById(companyID).get();
        Company company1 = modelMapper.map(companyDto, Company.class);

        if (company1.getCompanyName() != null) {
            company.setCompanyName(company1.getCompanyName());
        }

        if (company1.getTickerSymbol() != null) {
            company.setTickerSymbol(company1.getTickerSymbol());
        }

        return companyRepository.save(company);
    }

    @CacheEvict(value = "companies", keyGenerator = "customKeyGenerator")
    public void delete(long companyID) {
        companyRepository.deleteById(companyID);
    }

    private List<CompanyDto> mapAll(List<Company> companies) {
        return companies.stream().map(company -> modelMapper.map(company, CompanyDto.class)).toList();
    }
}
