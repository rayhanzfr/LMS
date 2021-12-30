package com.lawencon.lms.email;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.impl.BaseServiceLmsImpl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class FileSender extends BaseServiceLmsImpl {
	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	protected Configuration freemarkerConfig;

//	@Async
	public void sendReport(EmailHelper emailHelper, byte[] attachment) throws MessagingException {

		try {
			Users users = usersDao.findById(getIdAuth());

			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			DataSource dataSource = new ByteArrayDataSource(attachment, "application/pdf");

			helper.setFrom("lawenconassetsmanagement@gmail.com");
			helper.setTo(emailHelper.getReceiver());
			helper.setSubject(emailHelper.getSubject());
			String body = getEmailContent(emailHelper.getReceiver());
			helper.setText(body,true);
			helper.addAttachment(emailHelper.getAttachmentName(), dataSource);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getEmailContent(String to) throws IOException, TemplateException {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("to", to);
		freemarkerConfig.getTemplate("assets-expired.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}

}
