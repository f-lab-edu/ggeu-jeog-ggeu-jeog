package com.rollingpaper.ggeujeogggeujeog.board.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.exception.ExceptionCode;

public class DuplicatedTagsInBoardException extends BaseException {

	private static final String errorCode = ExceptionCode.DUPLICATED_TAGS_IN_BOARD.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "보드에 등록된 태그가 있습니다.";

	public DuplicatedTagsInBoardException() {
		this(errorCode, httpStatus, errorMessage);
	}

	public DuplicatedTagsInBoardException(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
