package com.rollingpaper.ggeujeogggeujeog.comment.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.error.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.error.ExceptionCode;

public class NoSuchCommentException extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_COMMENT.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 댓글입니다.";

	public NoSuchCommentException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchCommentException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
