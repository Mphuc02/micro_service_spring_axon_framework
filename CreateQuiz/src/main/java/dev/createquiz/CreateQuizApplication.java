package dev.createquiz;

import dev.common_service.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"dev.createquiz", "dev.common_service"})
@EnableDiscoveryClient
@Import(AxonConfig.class)
public class CreateQuizApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreateQuizApplication.class, args);
	}
}