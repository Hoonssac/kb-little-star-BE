package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.AuthErrorCode;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody Map<String, Integer> body, HttpSession session) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            throw new CustomException(AuthErrorCode.ACCESS_DENIED);
        }

        Integer mainPokemonId = body.get("main_pokemon_id");
        userService.updateMainPokemon(sessionUser.getId(), mainPokemonId);
        return ResponseEntity.ok("대표 포켓몬이 업데이트되었습니다.");
    }
}
