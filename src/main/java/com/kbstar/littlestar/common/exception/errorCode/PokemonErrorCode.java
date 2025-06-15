package com.kbstar.littlestar.common.exception.errorCode;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PokemonErrorCode implements ErrorCode{

	POKEMON_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "포켓몬을 찾을 수 없습니다."),
	EMPTY_POKEMON_LIST(HttpStatus.NOT_FOUND, "P-002", "포켓몬 목록이 비어 있습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
