package com.kbstar.littlestar.service;

import com.kbstar.littlestar.domain.Pokemon;
import com.kbstar.littlestar.domain.User;
import com.kbstar.littlestar.domain.UserPokemon;
import com.kbstar.littlestar.dto.SignupRequest;
import com.kbstar.littlestar.repository.PokemonRepository;
import com.kbstar.littlestar.repository.UserPokemonRepository;
import com.kbstar.littlestar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAge(request.getAge());
        user.setMileage(request.getMileage());
        user.setMainPokemonId(mainPokemon);

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