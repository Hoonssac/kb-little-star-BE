package com.kbstar.littlestar.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GatchaPokemonRequest {
	@NotNull
	private Long newPokemonId;
}
