package com.lawencon.lms;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.lawencon.lms.email.PasswordSender;

@SpringBootApplication
public class App {
	
//	@Autowired
//	private PasswordSender service;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//
//		try {
//			service.sendSimpleMessage("bahrul.faizi@gmail.com", "Coba", "halo");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
