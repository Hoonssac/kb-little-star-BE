package com.kbstar.littlestar.pokemon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonService {

	private final PokemonRepository pokemonRepository;

	public List<Pokemon> getAllPokemons() {
		List<Pokemon> pokemons = pokemonRepository.findAll();
		if (pokemons.isEmpty()) {
			throw new CustomException(PokemonErrorCode.EMPTY_POKEMON_LIST);
		}
		return pokemons;
	}

	public Pokemon getPokemonById(Long id) {
		return pokemonRepository.findById(id)
			.orElseThrow(() -> new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND));
	}
}
