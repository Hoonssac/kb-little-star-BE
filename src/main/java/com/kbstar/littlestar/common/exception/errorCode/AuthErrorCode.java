package com.kbstar.littlestar.common.exception.errorCode;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "A-001", "사용자가 존재하지 않습니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "A-002", "비밀번호가 틀렸습니다."),
	ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "A-003", "로그인이 필요합니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
