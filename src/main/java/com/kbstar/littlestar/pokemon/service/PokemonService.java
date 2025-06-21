package com.kbstar.littlestar.pokemon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonService {

	private final PokemonMapper pokemonMapper;

	public List<Pokemon> getAllPokemons() {
		List<Pokemon> pokemons = pokemonMapper.findAll();
		if (pokemons.isEmpty()) {
			throw new CustomException(PokemonErrorCode.EMPTY_POKEMON_LIST);
		}
		return pokemons;
	}

	public Pokemon getPokemonById(Long id) {
		Pokemon pokemon = pokemonMapper.findById(id);
		if (pokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}
		return pokemon;
	}
}
