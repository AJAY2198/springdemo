package com.example.demo.config;

import com.example.demo.greeting.GreetingFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {

    @Bean
    GreetingFormatter greetingFormatter(GreetingProperties properties) {
        return new GreetingFormatter(properties.getPrefix());
    }
}
