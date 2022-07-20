package com.capgemini.investingtradingappuserclient.client;

import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(path = "user/investingAccount")
public interface InvestingAccountClient {

    @PostMapping("/position")
    void buyPosition(@Valid @RequestBody final PositionDTO positionDTO);

    @PutMapping("/position/{positionID}")
    void sellPosition(@PathVariable final long positionID);

    @PutMapping("/{investingAccountID}/balance")
    void transferOUT(@PathVariable final long investingAccountID, @RequestParam final double amount);
}
