package com.BitGeekTalks.JanShayog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JanShayogApplication {

	@GetMapping("/")
	public String check(){
		return "Jan Shayog";
	}
	public static void main(String[] args) {
		SpringApplication.run(JanShayogApplication.class, args);
	}

}
