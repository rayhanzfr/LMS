package com.lawencon.lms.email;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lawencon.lms.service.impl.BaseServiceLmsImpl;

@Component
public class ReminderSender extends BaseServiceLmsImpl {
	@Autowired
	protected JavaMailSender mailSender;

	@Async
	public void sendReminder(EmailHelper emailHelper) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("lawenconassetsmanagement@gmail.com");
		helper.setTo(emailHelper.getReceiver());
		helper.setSubject(emailHelper.getSubject());
		helper.setText(emailHelper.getBody());

		mailSender.send(message);
	}

}
