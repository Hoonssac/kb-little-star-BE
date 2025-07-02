package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.AuthErrorCode;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.domain.UserPokemon;
import com.kbstar.littlestar.user.mapper.UserPokemonMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPokemonService {

	private final UserPokemonMapper userPokemonMapper;
	private final PokemonMapper pokemonMapper;

	@Transactional
	public void updateMainPokemon(Long mainPokemonId, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser == null) {
			throw new CustomException(AuthErrorCode.ACCESS_DENIED);
		}
		Pokemon pokemon = pokemonMapper.findById(mainPokemonId);
		if (pokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}

		userPokemonMapper.updateMainPokemon(sessionUser.getId(), pokemon.getId());

		// 세션 정보 업데이트
		sessionUser.updateMainPokemonId(mainPokemonId);
		session.setAttribute("user", sessionUser);
	}

	@Transactional
	public void gatchaPokemon(Long newPokemonId, HttpSession session) {
		User sessionUser = (User)session.getAttribute("user");
		if (sessionUser == null) {
			throw new CustomException(AuthErrorCode.ACCESS_DENIED);
		}

		Pokemon newPokemon = pokemonMapper.findById(newPokemonId);
		if (newPokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}

		UserPokemon userPokemon = UserPokemon.builder()
			.user(sessionUser)
			.pokemon(newPokemon)
			.build();

		userPokemonMapper.save(userPokemon);

		// 세션 정보 업데이트
		sessionUser.addPokemon(userPokemon);
		session.setAttribute("user", sessionUser);
	}
}
