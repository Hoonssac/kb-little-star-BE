package com.kbstar.littlestar.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMainPokemonRequest {
	@NotNull
	private Long mainPokemonId;
}
