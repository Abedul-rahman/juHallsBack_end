package com.swproj.SWProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableWebSecurity
@SpringBootApplication
public class SwProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwProjectApplication.class, args);
	}

}
