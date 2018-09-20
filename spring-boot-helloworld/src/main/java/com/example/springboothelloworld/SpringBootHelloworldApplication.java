package com.example.springboothelloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootHelloworldApplication.class);
//        springApplication.setBanner(null);
        springApplication.run(args);
//		SpringApplication.run(SpringBootHelloworldApplication.class, args);
    }
}
