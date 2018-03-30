package com.example.springbootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.springbootshiro.dao")
public class SpringBootShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}
}
