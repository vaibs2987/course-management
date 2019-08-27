package com.course.exception;

@SuppressWarnings("serial")
public class InvalidParamException extends RuntimeException {

	public InvalidParamException(String msg) {
		super(msg);
	}

	public InvalidParamException(String message, Exception rootCause) {
		super(message, rootCause);
	}
}
