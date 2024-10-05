package com.elo7.space_probe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpaceProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceProbeApplication.class, args);
	}

}
