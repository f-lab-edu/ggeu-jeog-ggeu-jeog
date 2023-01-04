package com.rollingpaper.ggeujeogggeujeog.authentication.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NotSameTokenException extends BaseException {

	private static final String errorCode = ExceptionCode.NOT_SAME_VERIFICATION_TOKEN.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "토큰이 일치하지 않습니다.";

	public NotSameTokenException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NotSameTokenException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
