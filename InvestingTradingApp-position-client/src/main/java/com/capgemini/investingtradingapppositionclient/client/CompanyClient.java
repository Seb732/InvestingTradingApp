package com.capgemini.investingtradingapppositionclient.client;

import com.capgemini.investingtradingapppositionclient.dto.CompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@FeignClient(name = "position-service", contextId = "company", path = "/company")
public interface CompanyClient {

    @PostMapping
    void create(@Valid @RequestBody final CompanyDto companyDto);

    @GetMapping
    List<CompanyDto> read(@Valid @RequestParam final Map<String, String> allParams);

    @PutMapping("/{companyID}")
    void update(@PathVariable final long companyID, @RequestBody final CompanyDto companyDto);

    @DeleteMapping
    void delete(@RequestParam final long companyID);
}
