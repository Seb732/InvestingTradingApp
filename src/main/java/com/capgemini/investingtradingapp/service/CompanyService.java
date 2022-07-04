package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.CompanyDto;
import com.capgemini.investingtradingapp.entity.Company;
import com.capgemini.investingtradingapp.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void save(CompanyDto companyDto) {
        companyRepository.save(modelMapper.map(companyDto, Company.class));
    }

    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream().map(company -> modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
    }

    public CompanyDto getByCompanyName(String name) {
        return modelMapper.map(companyRepository.findCompanyByCompanyName(name), CompanyDto.class);
    }

    public CompanyDto getByTickerSymbol(String tickerSymbol) {
        return modelMapper.map(companyRepository.findCompanyByTickerSymbol(tickerSymbol), CompanyDto.class);
    }

    public void update(long companyID, CompanyDto companyDto) {
        Company company = companyRepository.findById(companyID).get();
        Company company1 = modelMapper.map(companyDto, Company.class);

        if (company1.getCompanyName() != null) {
            company.setCompanyName(company1.getCompanyName());
        }

        if (company1.getTickerSymbol() != null) {
            company.setTickerSymbol(company1.getTickerSymbol());
        }

    }

    public void delete(long companyID) {
        companyRepository.deleteById(companyID);
    }

}
