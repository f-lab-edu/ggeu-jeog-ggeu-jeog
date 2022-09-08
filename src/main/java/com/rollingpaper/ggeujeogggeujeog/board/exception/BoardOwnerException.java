package com.rollingpaper.ggeujeogggeujeog.board.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.exception.ExceptionCode;

public class BoardOwnerException extends BaseException {

	private static final String errorCode = ExceptionCode.UNAUTHORIZED_ACCESS.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
	private static final String errorMessage = "보드 소유자가 아닙니다.";

	public BoardOwnerException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public BoardOwnerException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
