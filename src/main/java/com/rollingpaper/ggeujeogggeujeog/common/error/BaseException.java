package com.rollingpaper.ggeujeogggeujeog.common.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final String errorCode;
	private final HttpStatus httpStatus;

	protected BaseException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
}
