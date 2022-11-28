package com.techeeresc.tab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TabApplication {
	public static void main(String[] args) {
		SpringApplication.run(TabApplication.class, args);
	}
}
