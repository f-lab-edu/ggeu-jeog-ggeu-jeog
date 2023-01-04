package com.rollingpaper.ggeujeogggeujeog.user.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NoSuchUserException extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_USER.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 계정입니다.";

	public NoSuchUserException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchUserException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
