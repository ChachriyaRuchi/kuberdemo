package com.kubernet.kuberdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class KuberdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuberdemoApplication.class, args);
	}

	@GetMapping("/greeting")
	public String greeting(){
	return "Welcome to your first Kubernetes application.";
	}
}
