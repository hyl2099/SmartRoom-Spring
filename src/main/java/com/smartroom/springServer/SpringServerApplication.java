package com.smartroom.springServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;


@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringServerApplication {
//	@Value("${file.uploadFolder}")
//	private String uploadFolder;

	@RequestMapping("/hello")
	public String index(){
		return "Hello World， Spring boot is good";
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

//	@Bean
//	MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setLocation(uploadFolder);
//		return factory.createMultipartConfig();
//	}

//	@Bean(name="entityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		return sessionFactory;
//	}

}
