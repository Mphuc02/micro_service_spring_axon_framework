package dev.quiz_lab;

import dev.common_service.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"dev.quiz_lab", "dev.common_service"})
@EnableDiscoveryClient
@Import(AxonConfig.class)
@EnableConfigurationProperties
public class QuizApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
}
