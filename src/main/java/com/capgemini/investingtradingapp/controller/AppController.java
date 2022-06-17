package com.capgemini.investingtradingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
}
