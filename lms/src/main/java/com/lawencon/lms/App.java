package com.lawencon.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lawencon.lms.email.PasswordSender;

@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
