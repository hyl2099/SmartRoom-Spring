package com.smartroom.springServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;

@RestController
@SpringBootApplication(scanBasePackages={
		"com.smartroom.springServer.business_controllers","com.smartroom.springServer.data_services","com.smartroom.springServer.business_services"})
//		(exclude = {SecurityAutoConfiguration.class })
public class SpringServerApplication {

	@RequestMapping("/hello")
	public String index(){
		return "Hello World， Spring boot is good";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation("D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures");
		return factory.createMultipartConfig();
	}
}
