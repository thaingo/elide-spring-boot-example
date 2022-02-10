/*
 * Copyright 2019, Yahoo Inc.
 * Licensed under the Apache License, Version 2.0
 * See LICENSE file in project root for terms.
 */
package example;

import example.models.CompanyType;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.mvel2.ParserContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * Example app using elide-spring.
 */
@SpringBootApplication
@EntityScan
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RulesEngine defaultRulesEngine() {
        return new DefaultRulesEngine(new RulesEngineParameters());
    }

    @Bean
    public Rules ruleList() {
        return new Rules();
    }

    @Bean
    public ParserContext parserContext() {
        final ParserContext parserContext = new ParserContext();
        parserContext.addImport(CompanyType.class);
        return parserContext;
    }

}
