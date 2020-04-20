package com.solution.messageparser.config;

import com.solution.messageparser.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateServiceConfig {
    @Bean
    @Autowired
    public TemplateService templateService(){
        return new TemplateServiceImpl();
    }

    @Bean
    @Autowired
    public AttributeService attributeService(){
        return new AttributeServiceImpl();
    }

    @Bean
    @Autowired
    public Parser parser(){
        return new ParserImpl();

    }
}
