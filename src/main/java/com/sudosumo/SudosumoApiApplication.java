package com.sudosumo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SudosumoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudosumoApiApplication.class, args);
	}

}
