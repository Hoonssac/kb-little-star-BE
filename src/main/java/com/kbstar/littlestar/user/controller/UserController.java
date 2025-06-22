package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.user.dto.UpdateMainPokemonRequest;
import com.kbstar.littlestar.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody UpdateMainPokemonRequest request, HttpSession session) {
        userService.updateMainPokemon(request.getMainPokemonId(), session);
        return ResponseEntity.ok("대표 포켓몬이 업데이트되었습니다.");
    }
}
