package com.kontactsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kontactsphere.infrastructure.relationaldatabase.repository")
@EntityScan(basePackages = "com.kontactsphere.infrastructure.relationaldatabase.model")
public class KontactsphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(KontactsphereApplication.class, args);
	}

}
