package com.rollingpaper.ggeujeogggeujeog.user.Exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;

public class NoSuchUserException extends BaseException {

	private static final String errorCode = "002";
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 계정입니다.";

	public NoSuchUserException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchUserException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
