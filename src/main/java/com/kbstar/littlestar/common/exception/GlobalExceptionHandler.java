package com.kbstar.littlestar.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kbstar.littlestar.common.exception.errorCode.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		ErrorCode errorCode = ex.getErrorCode();

		Map<String, Object> response = new HashMap<>();
		response.put("code", errorCode.getCode());
		response.put("message", errorCode.getMessage());

		return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
	}
}
