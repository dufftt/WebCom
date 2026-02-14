package com.duft.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
