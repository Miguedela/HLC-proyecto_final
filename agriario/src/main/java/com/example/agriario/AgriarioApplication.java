package com.example.agriario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AgriarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgriarioApplication.class, args);

		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// System.out.println(encoder.encode("user123"));
	}
}
