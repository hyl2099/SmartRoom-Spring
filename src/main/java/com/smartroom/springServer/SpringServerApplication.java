package com.smartroom.springServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringServerApplication {
//	@Value("${file.uploadFolder}")
//	private String uploadFolder;

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
