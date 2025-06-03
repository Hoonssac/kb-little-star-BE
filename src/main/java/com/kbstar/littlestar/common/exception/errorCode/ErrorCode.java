package com.kbstar.littlestar.common.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	HttpStatus getHttStatus();
	String getCode();
	String getMessage();
}
