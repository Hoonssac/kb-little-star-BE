package com.kbstar.littlestar.pokemon.service;


import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kbstar.littlestar.pokemon.domain.Pokemon;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PokemonServiceTest {

	@Autowired
	private PokemonService pokemonService;

	@Test
	@DisplayName("전체 포켓몬 조회")
	void getAllPokemons() {
		List<Pokemon> pokemons = pokemonService.getAllPokemons();
		assertThat(pokemons).isNotEmpty();
	}

	@Test
	void getPokemonById() {
	}
}