package com.kbstar.littlestar.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.entity.User;
import com.kbstar.littlestar.user.entity.UserPokemon;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthServiceTest {

	@Autowired
	private AuthService authService;

	@Autowired
	private PokemonRepository pokemonRepository;

	@Test
	void 회원가입_성공_및_보유포켓몬_등록확인() {
		// given
		Pokemon pokemon = Pokemon.builder()
			.id(1L)
			.name("피카츄")
			.imageUrl("https://example.com/pikachu.png")
			.height(0.4)
			.weight(6.0)
			.types("전기")
			.build();
		pokemonRepository.save(pokemon);

		SignupRequest request = new SignupRequest();
		request.setUsername("피카츄123");
		request.setPassword("pikachu123");
		request.setAge(10);
		request.setMileage(0);
		request.setMainPokemonId(pokemon.getId());

		// when
		User user = authService.signup(request);

		// then
		assertNotNull(user.getId());
		assertEquals("피카츄123", user.getUsername());
		assertEquals(10, user.getAge());
		assertEquals(pokemon.getId(), user.getMainPokemon().getId());

		List<UserPokemon> userPokemons = user.getUserPokemons();
		assertEquals(1, userPokemons.size());
		assertEquals(pokemon.getId(), userPokemons.get(0).getPokemon().getId());
	}
}