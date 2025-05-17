package com.kbstar.littlestar.controller;

import com.kbstar.littlestar.domain.Pokemon;
import com.kbstar.littlestar.domain.User;
import com.kbstar.littlestar.repository.PokemonRepository;
import com.kbstar.littlestar.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody Map<String, Integer> body, HttpSession session) {
        System.out.println("🔥 PATCH 요청 도달!");

        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("🚫 세션에 유저 없음!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Integer mainPokemonId = body.get("main_pokemon_id");
        Pokemon mainPokemon = pokemonRepository.findById(Long.valueOf(mainPokemonId))
                .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 존재하지 않습니다."));

        user.setMainPokemon(mainPokemon);
        userRepository.save(user);

        System.out.println("✅ 대표 포켓몬 설정 완료: " + mainPokemonId);
        return ResponseEntity.ok("대표 포켓몬이 업데이트되었습니다.");
    }

    @GetMapping("/hi")
    public static void hello() {
        System.out.println("hihi");

    }
}
