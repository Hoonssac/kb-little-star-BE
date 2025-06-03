package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.user.entity.User;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.repository.UserRepository;
import com.kbstar.littlestar.user.service.UserService;

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
    private final UserService userService;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody Map<String, Integer> body, HttpSession session) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Integer mainPokemonId = body.get("main_pokemon_id");
        userService.updateMainPokemon(sessionUser, mainPokemonId);
        return ResponseEntity.ok("대표 포켓몬이 업데이트되었습니다.");
    }
}
