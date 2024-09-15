package com.rollingpaper.ggeujeogggeujeog.user.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class DuplicatedEmailException extends BaseException {

	private static final String errorCode = ExceptionCode.DUPLICATED_EMAIL.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "중복된 이메일이 존재합니다.";

	public DuplicatedEmailException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public DuplicatedEmailException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
