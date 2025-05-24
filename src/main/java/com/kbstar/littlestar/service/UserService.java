package com.kbstar.littlestar.service;

import com.kbstar.littlestar.domain.Pokemon;
import com.kbstar.littlestar.domain.User;
import com.kbstar.littlestar.domain.UserPokemon;
import com.kbstar.littlestar.dto.SignupRequest;
import com.kbstar.littlestar.dto.UserResponse;
import com.kbstar.littlestar.repository.PokemonRepository;
import com.kbstar.littlestar.repository.UserPokemonRepository;
import com.kbstar.littlestar.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
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

    // Dto 변환
    public UserResponse toUserResponse(User user) {
        List<Long> pokemonIds = user.getUserPokemons()
                .stream()
                .map(up -> up.getPokemon().getId())
                .collect(Collectors.toList());

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getAge(),
                user.getLastAnsweredDate(),
                user.getMileage(),
                user.getMainPokemon().getId(),
                pokemonIds
        );
    }

    @Transactional
    public void updateMainPokemon(User user, Integer mainPokemonId) {
        Pokemon pokemon = pokemonRepository.findById(Long.valueOf(mainPokemonId))
            .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 존재하지 않습니다."));

        user.changeMainPokemon(pokemon);
        userRepository.save(user);
    }
}