package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarclaysStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarclaysStoreApplication.class, args);
	}

}
