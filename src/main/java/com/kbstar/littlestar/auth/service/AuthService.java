package com.kbstar.littlestar.auth.service;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.AuthErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.user.entity.User;
import com.kbstar.littlestar.user.entity.UserPokemon;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.repository.UserPokemonRepository;
import com.kbstar.littlestar.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final PokemonRepository pokemonRepository;
	private final UserPokemonRepository userPokemonRepository;

	public User signup(SignupRequest request) {
		// 메인 포켓몬 엔티티 조회
		Pokemon mainPokemon = pokemonRepository.findById(request.getMainPokemonId())
			.orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 존재하지 않습니다."));

		// user 객체 생성
		User user = User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.age(request.getAge())
			.mileage(request.getMileage())
			.mainPokemon(mainPokemon)
			.build();

		// user 저장
		user.addPokemon(mainPokemon);

		return userRepository.save(user);
	}

	// 로그인
	public User login(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

		// 비밀번호 불일치
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new CustomException(AuthErrorCode.INVALID_PASSWORD);
		}

		return user;
	}
}