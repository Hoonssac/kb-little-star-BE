package com.kbstar.littlestar.auth.service;

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

	// 회원가입
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
		User savedUser = userRepository.save(user);

		// 보유 포켓몬 연관 관계 저장
		UserPokemon userPokemon = new UserPokemon(savedUser, mainPokemon);
		userPokemonRepository.save(userPokemon);
		return savedUser;
	}

	// 로그인
	public User login(String username, String password) {
		// user 찾기
		User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

		// 비밀번호 불일치
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 틀립니다.");
		}

		return user;
	}
}