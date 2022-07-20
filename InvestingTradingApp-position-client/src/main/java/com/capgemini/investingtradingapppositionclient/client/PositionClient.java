package com.capgemini.investingtradingapppositionclient.client;

import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
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

@FeignClient(path = "/position")
public interface PositionClient {

    @PostMapping
    void create(@Valid @RequestBody final PositionDTO position);

    @GetMapping
    List<PositionDTO> read(@RequestParam final Map<String, String> allParams);

    @PutMapping("/{positionID}")
    void update(@Valid @RequestBody final PositionDTO positionDTO, @PathVariable final long positionID);

    @DeleteMapping
    void delete(@RequestParam final long positionID);
}
