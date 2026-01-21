package com.example.demo.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
public class ApiRequestException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -974675113254259818L;

	private final int responseStatus;
	private final List<ApiRequestError> errors = new ArrayList<>();

	public ApiRequestException(String message) {
		this(message, 200, null, null);
	}

	public ApiRequestException(String message, int responseStatus, List<ApiRequestError> errors) {
		this(message, responseStatus, null, null);
		this.errors.addAll(errors);
	}

	public ApiRequestException(String message, int responseStatus) {
		this(message, responseStatus, null, null);
	}

	public ApiRequestException(String message, int responseStatus, String errorCode) {
		this(message, responseStatus, null, errorCode);
	}

	public ApiRequestException(String message, int responseStatus, String property, String errorCode) {
		super(message);
		this.responseStatus = responseStatus;
		addError(property, errorCode);
	}

	public ApiRequestException(String message, Throwable cause) {
		this(message, cause, 200, null, null);
	}

	public ApiRequestException(String message, Throwable cause, int responseStatus, List<ApiRequestError> errors) {
		this(message, cause, responseStatus, null, null);
		this.errors.addAll(errors);
	}

	public ApiRequestException(String message, Throwable cause, int responseStatus) {
		this(message, cause, responseStatus, null, null);
	}

	public ApiRequestException(String message, Throwable cause, int responseStatus, String errorCode) {
		this(message, cause, responseStatus, null, errorCode);
	}

	public ApiRequestException(String message, Throwable cause, int responseStatus, String property, String errorCode) {
		super(message, cause);
		this.responseStatus = responseStatus;
		addError(property, errorCode);
	}

	public void addError(String property, String errorCode) {
		if (StringUtils.isNotEmpty(errorCode)) {
			errors.add(new ApiRequestError(property, errorCode));
		}
	}
}
