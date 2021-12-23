package com.lawencon.lms.email;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.service.impl.BaseServiceLmsImpl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class ReminderSender extends BaseServiceLmsImpl {
	@Autowired
	protected JavaMailSender mailSender;
	
	@Autowired
	protected Configuration freemarkerConfig;

	@Async
	public void sendReminder(TransactionsDetailOut transactionsDetailOut,EmailHelper emailHelper) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		String bodyMail = null;
		try {
			bodyMail = getEmailContent(emailHelper.getReceiver(), transactionsDetailOut);
		} catch (IOException e) {
			e.printStackTrace();
			rollback();
		} catch (TemplateException e) {
			e.printStackTrace();
			rollback();
		}
		helper.setFrom("lawenconassetsmanagement@gmail.com");
		helper.setTo(emailHelper.getReceiver());
		helper.setSubject(emailHelper.getSubject());
		if(bodyMail!=null) {
			helper.setText(bodyMail,true);
		}

		mailSender.send(message);
	}
	
	public String getEmailContent(String to, TransactionsDetailOut transactionsDetailOut) throws IOException, TemplateException {
		StringWriter stringWriter = new StringWriter();
		
		String items = transactionsDetailOut.getAssets().getItems().getItemsName();
		String assets = transactionsDetailOut.getAssets().getAssetsName();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
		LocalDate expiredDate = transactionsDetailOut.getTransactionDetailOutExpired();
		String expired = expiredDate.format(dateFormat);
		
		Map<String, Object> model = new HashMap<>();
		model.put("to", to);		
		model.put("items", items);
		model.put("assets", assets);
		model.put("expired", expired);
		
		freemarkerConfig.getTemplate("transactions-reminder.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}
}
