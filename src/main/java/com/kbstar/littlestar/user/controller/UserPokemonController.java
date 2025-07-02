package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.user.dto.GatchaPokemonRequest;
import com.kbstar.littlestar.user.dto.UpdateMainPokemonRequest;
import com.kbstar.littlestar.user.dto.UserResponse;

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
    public ResponseEntity<UserResponse> updateMainPokemon(@RequestBody UpdateMainPokemonRequest request, HttpSession session) {
        UserResponse response = userPokemonService.updateMainPokemon(request.getMainPokemonId(), session);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gatcha")
    public ResponseEntity<UserResponse> gatchaPokemon(@RequestBody GatchaPokemonRequest request, HttpSession session) {
        UserResponse response = userPokemonService.gatchaPokemon(request.getNewPokemonId(), session);
        return ResponseEntity.ok(response);
    }
}
