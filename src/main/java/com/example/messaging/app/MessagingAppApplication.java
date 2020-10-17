package com.example.messaging.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.messaging.app.config.JmsConfiguration;

@SpringBootApplication
@EnableScheduling
@Import({ JmsConfiguration.class })
@EnableRetry
public class MessagingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingAppApplication.class, args);
	}

}
