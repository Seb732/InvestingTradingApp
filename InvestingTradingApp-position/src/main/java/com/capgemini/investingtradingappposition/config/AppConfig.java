package com.capgemini.investingtradingappposition.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig extends CachingConfigurerSupport {
    @Bean("modelMapper2")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("customKeyGen")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
