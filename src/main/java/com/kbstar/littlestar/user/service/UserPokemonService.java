package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.user.mapper.UserMapper;
import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.AuthErrorCode;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.mapper.UserPokemonMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPokemonService {

	private final UserMapper userMapper;
	private final UserPokemonMapper userPokemonMapper;
	private final PokemonMapper pokemonMapper;

	@Transactional
	public void updateMainPokemon(Integer mainPokemonId, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser == null) {
			throw new CustomException(AuthErrorCode.ACCESS_DENIED);
		}
		Pokemon pokemon = pokemonMapper.findById(Long.valueOf(mainPokemonId));
		if (pokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}
		userPokemonMapper.updateMainPokemon(sessionUser.getId(), pokemon.getId());
	}

	public void gatchaPokemon(Integer newPokemonId, HttpSession session) {
		User sessionUser = (User)session.getAttribute("user");
		if (sessionUser == null) {
			throw new CustomException(AuthErrorCode.ACCESS_DENIED);
		}
	}
}
