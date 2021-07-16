package com.exercise.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImplApplication.class, args);
    }

}
