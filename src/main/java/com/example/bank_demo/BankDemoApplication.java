package com.example.bank_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bank_demo.model.Deposit;

@SpringBootApplication
public class BankDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDemoApplication.class, args);
	}
	
	@Bean
	public Deposit getDeposit() {
		return new Deposit();
	}
	
	

}
