package com.kbstar.littlestar.service;

import com.kbstar.littlestar.domain.User;
import com.kbstar.littlestar.dto.SignupRequest;
import com.kbstar.littlestar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public User signup(SignupRequest request) {
        // user 추가
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setAge(request.getAge());
        user.setMileage(request.getMileage());
        user.setPokemonIds(request.getPokemonIds());
        user.setMainPokemonId(request.getMainPokemonId());
        user.setQuestionIds(request.getQuestionIds());
        user.setTicketCount(request.getTicketCount());
        user.setLastAnsweredDate(null);
        return userRepository.save(user);
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