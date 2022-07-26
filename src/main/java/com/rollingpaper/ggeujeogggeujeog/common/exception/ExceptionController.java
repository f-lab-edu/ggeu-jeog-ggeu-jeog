package com.rollingpaper.ggeujeogggeujeog.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ExceptionResponse> baseExceptionHandler(BaseException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			exception.getErrorCode(),
			exception.getMessage()
		);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, exception.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> validExceptionHandler(MethodArgumentNotValidException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			ExceptionCode.INVALID_ARGUMENT.getErrorCode(),
			exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
		);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> argumentExceptionHandler(IllegalArgumentException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			ExceptionCode.ILLEGAL_PASSWORD.getErrorCode(),
			exception.getMessage()
		);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
