package com.rollingpaper.ggeujeogggeujeog.common.exception;

public enum ExceptionCode {

	DUPLICATED_EMAIL("001"),
	NO_SUCH_USER("002"),
	UNAUTHORIZED_ACCESS("003"),
	NO_SUCH_BOARD("101"),
	NO_SUCH_PAPER("201"),
	NO_SUCH_COMMENT("301"),
	COMMENT_ALREADY_EXIST("302"),
	ILLEGAL_PASSWORD("401"),
	INVALID_ARGUMENT("402"),
	NO_SUCH_NOTIFICATION("501");

	private String errorCode;

	ExceptionCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
