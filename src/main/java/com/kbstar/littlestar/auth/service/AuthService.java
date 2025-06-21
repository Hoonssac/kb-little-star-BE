package com.kbstar.littlestar.auth.service;

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

	public User signup(SignupRequest request) {
		// 메인 포켓몬 엔티티 조회
		Pokemon mainPokemon = pokemonMapper.findById(request.getMainPokemonId());
		if (mainPokemon == null) {
			throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
		}

		// user 객체 생성
		User user = User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.age(request.getAge())
			.mileage(request.getMileage())
			.mainPokemonId(mainPokemon.getId())
			.build();

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

		return user;
	}

	public User login(String username, String password) {
		User user = userMapper.findByUserName(username);
		if (user == null) {
			throw new CustomException(AuthErrorCode.USER_NOT_FOUND);
		}

		// 비밀번호 불일치
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new CustomException(AuthErrorCode.INVALID_PASSWORD);
		}

		return user;
	}
}