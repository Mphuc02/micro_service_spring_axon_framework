package dev.quiz_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuizLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizLabApplication.class, args);
	}

}
