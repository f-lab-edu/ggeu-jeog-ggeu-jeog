package com.rollingpaper.ggeujeogggeujeog.user.Exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;

public class DuplicatedEmailException extends BaseException {

	private static final String errorCode = "001";
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "중복된 이메일이 존재합니다.";

	public DuplicatedEmailException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public DuplicatedEmailException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
