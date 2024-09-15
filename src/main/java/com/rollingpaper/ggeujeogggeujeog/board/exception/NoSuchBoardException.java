package com.rollingpaper.ggeujeogggeujeog.board.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NoSuchBoardException extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_BOARD.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 보드입니다.";

	public NoSuchBoardException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchBoardException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
