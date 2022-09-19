package com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure;

import javax.mail.MessagingException;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.EmailMessage;

public interface EmailSender {
	void sendEmail(EmailMessage message) throws MessagingException;
}
