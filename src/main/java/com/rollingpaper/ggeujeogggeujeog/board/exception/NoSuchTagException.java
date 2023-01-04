package com.rollingpaper.ggeujeogggeujeog.board.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NoSuchTagException extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_TAG.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 태그입니다.";

	public NoSuchTagException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchTagException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
