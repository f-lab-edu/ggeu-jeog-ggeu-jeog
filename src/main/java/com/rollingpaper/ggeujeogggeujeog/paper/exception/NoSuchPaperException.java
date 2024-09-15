package com.rollingpaper.ggeujeogggeujeog.paper.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NoSuchPaperException extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_PAPER.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 페이퍼입니다.";

	public NoSuchPaperException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchPaperException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
