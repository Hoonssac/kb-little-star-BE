package com.kbstar.littlestar.user.controller;

import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.dto.GatchaPokemonRequest;
import com.kbstar.littlestar.user.dto.UpdateMainPokemonRequest;
import com.kbstar.littlestar.user.dto.UserResponse;

import com.kbstar.littlestar.user.service.UserPokemonService;
import com.kbstar.littlestar.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserPokemonController {

    private final UserPokemonService userPokemonService;
    private final UserService userService;

    @PatchMapping("/main-pokemon")
    public ResponseEntity<?> updateMainPokemon(@RequestBody UpdateMainPokemonRequest request, HttpSession session) {
        userPokemonService.updateMainPokemon(request.getMainPokemonId(), session);
        User user = (User) session.getAttribute("user");
        UserResponse response = userService.toUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gatcha")
    public ResponseEntity<?> gatchaPokemon(@RequestBody GatchaPokemonRequest request, HttpSession session) {
        userPokemonService.gatchaPokemon(request.getNewPokemonId(), session);
        User user = (User) session.getAttribute("user");
        UserResponse response = userService.toUserResponse(user);
        return ResponseEntity.ok(response);
    }
}
