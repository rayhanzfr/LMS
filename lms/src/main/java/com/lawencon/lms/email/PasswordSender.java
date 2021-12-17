package com.lawencon.lms.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PasswordSender {
	@Autowired
	protected JavaMailSender mailSender;

	@Async
	public void sendSimpleMessage(String to, String subject, String text) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("noreply@baeldung.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText("<b>" + text + "</b>", true);
		mailSender.send(message);
	}

}
