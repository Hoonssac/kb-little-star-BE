package com.kbstar.littlestar.common.exception.errorCode;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_001", "사용자가 존재하지 않습니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_002", "비밀번호가 틀렸습니다.");

	private final HttpStatus httpStatus;
	private String code;
	private String message;

	@Override
	public HttpStatus getHttStatus() {
		return httpStatus;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
