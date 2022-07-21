package com.capgemini.investingtradingappuserclient.client;

import com.capgemini.investingtradingappuserclient.dto.UserDTO;
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

@FeignClient(name = "user-service", contextId = "user", path = "/user")
public interface UserClient {

    @PostMapping
    void save(@Valid @RequestBody UserDTO userDTO);

    @DeleteMapping
    void delete(@RequestParam final long userID);

    @PutMapping("/{userID}")
    void update(@PathVariable final long userID, @Valid @RequestBody final UserDTO user);

    @GetMapping
    List<UserDTO> read(@RequestParam final Map<String, String> allParams);
}
