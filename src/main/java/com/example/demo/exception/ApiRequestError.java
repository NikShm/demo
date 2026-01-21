package com.example.demo.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiRequestError {

	private final String property;
	private final String errorCode;

	public ApiRequestError(String errorCode) {
		this(null, errorCode);
	}

	public ApiRequestError(String property, String errorCode) {
		this.property = property;
		this.errorCode = errorCode;
	}
}
