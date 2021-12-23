package com.lawencon.lms.email;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class PasswordSender {
	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	protected Configuration freemarkerConfig;

	@Async
	public void sendSimpleMessage(String to, String subject, String text) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("lawenconassetsmanagement@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		String emailContent = getEmailContent(to,text);
		helper.setText(emailContent, true);
		mailSender.send(message);
	}

	public String getEmailContent(String to,String text) throws IOException, TemplateException {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("to", to);
		model.put("text", text);
		freemarkerConfig.getTemplate("pass-email.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}
}
