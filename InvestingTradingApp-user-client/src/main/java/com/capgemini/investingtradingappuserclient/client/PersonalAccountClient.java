package com.capgemini.investingtradingappuserclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(path = "/user/personalAccount", name = "localhost:8080")
public interface PersonalAccountClient {

    @PutMapping("/{personalAccountID}/balance")
    void depositOrWithdraw(@RequestParam final String operationType, @PathVariable final long personalAccountID, @RequestParam final double amount);


}
