package com.api.mycologie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MycologieApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MycologieApplication.class, args);
	}

	public void run(String... args) throws Exception {
 		System.out.println("L'application est démarré !");
	}
}
