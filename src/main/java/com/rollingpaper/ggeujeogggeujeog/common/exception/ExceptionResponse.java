package com.rollingpaper.ggeujeogggeujeog.common.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {

	private String errorCode;
	private String errorMessage;

	public ExceptionResponse(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
