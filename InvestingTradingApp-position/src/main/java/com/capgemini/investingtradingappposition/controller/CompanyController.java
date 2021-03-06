package com.capgemini.investingtradingappposition.controller;

import com.capgemini.investingtradingappposition.service.CompanyService;
import com.capgemini.investingtradingapppositionclient.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private static final String CIRCUIT_SERVICE = "companyService";
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final CompanyDto companyDto) {

        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> companyService.save(companyDto));
    }

    @GetMapping
    public List<CompanyDto> read(@Valid @RequestParam final Map<String, String> allParams) {

        if (allParams.containsKey("companyID")) {
            return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() ->
                    companyService.getByCompanyID(Long.parseLong(allParams.get("companyID"))));
        }

        if (allParams.containsKey("companyName")) {
            return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> companyService.getByCompanyName(allParams.get("companyName")));
        }

        if (allParams.containsKey("tickerSymbol")) {
            return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> companyService.getByTickerSymbol(allParams.get("tickerSymbol")));
        }

        return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> companyService.getAll());
    }

    @PutMapping("/{companyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final long companyID, @RequestBody final CompanyDto companyDto) {
        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> companyService.update(companyID, companyDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long companyID) {
        companyService.delete(companyID);
    }
}
