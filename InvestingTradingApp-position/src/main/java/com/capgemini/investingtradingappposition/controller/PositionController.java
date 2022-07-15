package com.capgemini.investingtradingappposition.controller;

import com.capgemini.investingtradingappposition.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappposition.exception.PositionNotFoundException;
import com.capgemini.investingtradingappposition.service.PositionService;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void save(@Valid @RequestBody final PositionDTO position, @RequestParam final long investingAccountID) {
//        positionService.save(investingAccountID, position);
//    }
    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyPosition(@PathVariable final long investingAccountID, @Valid @RequestBody final PositionDTO positionDTO) throws InsufficientFoundsException {
        positionService.buyPosition(investingAccountID, positionDTO);
    }

    @GetMapping
    public List<PositionDTO> read(@RequestParam final Map<String, String> allParams) {
        if (allParams.containsKey("companyID")) {
            return positionService.getByCompanyID(Long.parseLong(allParams.get("companyID")));
        }
        if (allParams.containsKey("openDate")) {
            return positionService.getByOpenDateAfter(LocalDateTime.parse(allParams.get("openDate"),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        }
        if (allParams.containsKey("ticker")) {
            return positionService.getPositionByTickerGreaterThan(Double.parseDouble(allParams.get("ticker")));
        }
        return positionService.getAll();
    }

//    @PutMapping("/{positionID}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@Valid @RequestBody final PositionDTO positionDTO, @PathVariable final long positionID) {
//        positionService.update(positionID, positionDTO);
//    }
    @PutMapping("/position/{positionID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellPosition(@PathVariable final long investingAccountID, @PathVariable final long positionID) throws PositionNotFoundException, PositionNotFoundException {
        positionService.sellPosition(investingAccountID, positionID);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long positionID) {
        positionService.delete(positionID);
    }
}
