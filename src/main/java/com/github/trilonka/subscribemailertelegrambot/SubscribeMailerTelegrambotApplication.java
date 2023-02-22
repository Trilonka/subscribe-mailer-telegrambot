package com.github.trilonka.subscribemailertelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscribeMailerTelegrambotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscribeMailerTelegrambotApplication.class, args);
	}

}
