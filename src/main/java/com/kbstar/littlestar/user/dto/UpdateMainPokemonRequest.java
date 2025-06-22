package com.kbstar.littlestar.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMainPokemonRequest {
	@NotNull
	@JsonProperty("main_pokemon_id")
	private Integer mainPokemonId;
}
