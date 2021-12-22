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

import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.impl.BaseServiceLmsImpl;

@Component
public class FileSender extends BaseServiceLmsImpl {
	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	private UsersDao usersDao;

	@Async
	public void sendFile(String to, String subject, String body, byte[] attachment) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		DataSource dataSource = new ByteArrayDataSource(attachment, "application/pdf");

		helper.setFrom("bahrul.faizi@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText("<b>" + body + "</b>", true);
		helper.addAttachment("Assets-report.pdf", dataSource);

		mailSender.send(message);
	}

	@Async
	public void sendReport(EmailHelper emailHelper, byte[] attachment) throws MessagingException {

		try {
			Users users = usersDao.findById(getIdAuth());

			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			DataSource dataSource = new ByteArrayDataSource(attachment, "application/pdf");

			helper.setFrom("bahrul.faizi@gmail.com");
			helper.setTo(users.getUsersEmail());
			helper.setSubject(emailHelper.getSubject());
			helper.setText(emailHelper.getBody());
			helper.addAttachment(emailHelper.getAttachmentName(), dataSource);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
