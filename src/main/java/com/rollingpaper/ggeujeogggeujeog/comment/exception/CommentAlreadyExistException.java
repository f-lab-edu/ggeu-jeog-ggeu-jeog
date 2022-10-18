package com.rollingpaper.ggeujeogggeujeog.comment.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.exception.ExceptionCode;

public class CommentAlreadyExistException extends BaseException {

	private static final String errorCode = ExceptionCode.COMMENT_ALREADY_EXIST.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "댓글이 이미 존재합니다.";

	public CommentAlreadyExistException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public CommentAlreadyExistException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
