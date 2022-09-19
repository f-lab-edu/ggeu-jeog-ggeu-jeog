package com.rollingpaper.ggeujeogggeujeog.notification.exception;

import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;
import com.rollingpaper.ggeujeogggeujeog.common.exception.ExceptionCode;

public class NoSuchNotification extends BaseException {

	private static final String errorCode = ExceptionCode.NO_SUCH_NOTIFICATION.getErrorCode();
	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private static final String errorMessage = "존재하지 않는 알림입니다.";

	public NoSuchNotification() {
		this(errorCode, httpStatus, errorMessage);
	}

	public NoSuchNotification(String errorCode, HttpStatus httpStatus, String errorMessage) {
		super(errorCode, httpStatus, errorMessage);
	}
}
