package ru.otus.zarechnev.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@SpringBootApplication
public class SprgIntegrApp {

    public static void main(String[] args) {
        SpringApplication.run(SprgIntegrApp.class, args);
    }
}
