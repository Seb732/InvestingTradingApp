package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.CompanyDto;
import com.capgemini.investingtradingapp.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody final CompanyDto companyDto) {
        companyService.save(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> read(@RequestParam final Map<String, String> allParams) {

        if (allParams.containsKey("companyName")) {
            return List.of(companyService.getByCompanyName(allParams.get("companyName")));
        }

        if (allParams.containsKey("tickerSymbol")) {
            return List.of(companyService.getByTickerSymbol(allParams.get("tickerSymbol")));
        }

        return companyService.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam final long id, @RequestParam final CompanyDto companyDto) {
        companyService.update(id, companyDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long id) {
        companyService.delete(id);
    }
}