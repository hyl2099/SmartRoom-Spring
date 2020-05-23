package com.smartroom.springServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringServerApplication {

	@RequestMapping("/hello")
	public String index(){
		return "Hello World， Spring boot is good";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}
}
