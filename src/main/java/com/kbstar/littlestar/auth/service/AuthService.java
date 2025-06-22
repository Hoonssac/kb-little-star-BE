package com.kbstar.littlestar.auth.service;

import java.util.List;

import com.kbstar.littlestar.auth.dto.LoginRequest;
import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.AuthErrorCode;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.user.domain.UserPokemon;
import com.kbstar.littlestar.user.mapper.UserMapper;
import com.kbstar.littlestar.user.mapper.UserPokemonMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final PokemonMapper pokemonMapper;
	private final UserPokemonMapper userPokemonMapper;
	private final UserMapper userMapper;

	public User signup(SignupRequest request, HttpSession session) {
		// 메인 포켓몬 조회
		Pokemon mainPokemon = pokemonMapper.findById(request.getMainPokemonId());
		if (mainPokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}

		User user = User.of(request, passwordEncoder.encode(request.getPassword()));

		// user 저장
		userMapper.save(user);

		// 보유 포켓몬 생성
		UserPokemon userPokemon = UserPokemon.builder()
			.user(user)
			.pokemon(mainPokemon)
			.build();

		// 보유 포켓몬 저장
		userPokemonMapper.save(userPokemon);

		// 유저 객체 안에도 수동으로 넣어줌
		if (user.getUserPokemons() == null) {
			user.addPokemon(userPokemon);
		}
		user.getUserPokemons().add(userPokemon);

		session.setAttribute("user", user);

		return user;
	}

	public User login(LoginRequest request, HttpSession session) {
		User user = userMapper.findByUserName(request.getUsername());
		if (user == null) {
			throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
		}

		// 비밀번호 불일치
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new CustomException(AuthErrorCode.INVALID_PASSWORD);
		}

		// 보유 포켓몬 주입
		List<UserPokemon> pokemons = userPokemonMapper.findByUserId(user.getId());
		pokemons.forEach(user::addPokemon);

		// 세션 저장
		session.setAttribute("user", user);

		return user;
	}

	public User getUserInfo(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
		}

		// 기존 포켓몬 목록 초기화
		user.getUserPokemons().clear();

		List<UserPokemon> userPokemons = userPokemonMapper.findByUserId(user.getId());
		userPokemons.forEach(user::addPokemon);
		return user;
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}
}