package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.CompanyDto;
import com.capgemini.investingtradingapp.entity.Company;
import com.capgemini.investingtradingapp.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CachePut(value = "companies", keyGenerator = "customKeyGenerator")
    public void save(CompanyDto companyDto) {
        companyRepository.save(modelMapper.map(companyDto, Company.class));
    }

    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getAll() {
        return mapAll(companyRepository.findAll());
    }

    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getByCompanyName(String name) {
        return mapAll(companyRepository.findCompanyByCompanyName(name));
    }

    @Cacheable(value = "companies", keyGenerator = "customKeyGenerator")
    public List<CompanyDto> getByTickerSymbol(String tickerSymbol) {
        return mapAll(companyRepository.findCompanyByTickerSymbol(tickerSymbol));
    }

    @CachePut(value = "companies", keyGenerator = "customKeyGenerator")
    public void update(long companyID, CompanyDto companyDto) {
        Company company = companyRepository.findById(companyID).get();
        Company company1 = modelMapper.map(companyDto, Company.class);

        if (company1.getCompanyName() != null) {
            company.setCompanyName(company1.getCompanyName());
        }

        if (company1.getTickerSymbol() != null) {
            company.setTickerSymbol(company1.getTickerSymbol());
        }
        companyRepository.save(company);
    }

    @CacheEvict(value = "companies", keyGenerator = "customKeyGenerator")
    public void delete(long companyID) {
        companyRepository.deleteById(companyID);
    }

    private List<CompanyDto> mapAll(List<Company> companies) {
        return companies.stream().map(company -> modelMapper.map(company, CompanyDto.class)).toList();
    }
}
