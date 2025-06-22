package com.kbstar.littlestar.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kbstar.littlestar.common.exception.errorCode.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Validation 예외 처리
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(Map.of(
			"code", "VALIDATION_ERROR",
			"message", "입력값을 다시 확인해주세요.",
			"errors", errors
		));
	}

	// 커스텀 예외 처리
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		ErrorCode errorCode = ex.getErrorCode();

		return ResponseEntity.status(errorCode.getHttpStatus()).body(Map.of(
			"code", errorCode.getCode(),
			"message", errorCode.getMessage()
		));
	}
}
