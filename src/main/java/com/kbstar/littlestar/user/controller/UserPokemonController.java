package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.user.dto.GatchaPokemonRequest;
import com.kbstar.littlestar.user.dto.UpdateMainPokemonRequest;
import com.kbstar.littlestar.user.service.UserPokemonService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserPokemonController {

    private final UserPokemonService userPokemonService;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody UpdateMainPokemonRequest request, HttpSession session) {
        userPokemonService.updateMainPokemon(request.getMainPokemonId(), session);
        return ResponseEntity.ok("대표 포켓몬이 업데이트되었습니다.");
    }

    @PostMapping("/gatcha")
    public ResponseEntity<?> gatchaPokemon(@RequestBody GatchaPokemonRequest request, HttpSession session) {
        userPokemonService.gatchaPokemon(request.getNewPokemonId(), session);
        return ResponseEntity.ok("새로운 포켓몬이 추가되었습니다.");
    }
}
