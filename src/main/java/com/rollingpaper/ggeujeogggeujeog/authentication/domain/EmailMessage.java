package com.rollingpaper.ggeujeogggeujeog.authentication.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {

	private String to;
	private String subject;
	private String message;

	@Builder
	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}
}
