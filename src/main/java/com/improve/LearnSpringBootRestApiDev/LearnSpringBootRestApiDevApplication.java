package com.improve.LearnSpringBootRestApiDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.improve.*")
@EnableJpaRepositories(basePackages = "com.improve.*")
@EntityScan("com.improve.*")
public class LearnSpringBootRestApiDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringBootRestApiDevApplication.class, args);
	}
}
