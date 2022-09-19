package com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.EmailMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JavaEmailSender implements EmailSender {

	private final JavaMailSender emailSender;

	@Override
	public void sendEmail(EmailMessage message) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(message.getTo());
		helper.setSubject(message.getSubject());
		helper.setText(message.getMessage());
		emailSender.send(mimeMessage);
	}
}
