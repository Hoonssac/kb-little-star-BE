package com.kbstar.littlestar.auth.controller;

import com.kbstar.littlestar.auth.dto.LoginRequest;
import com.kbstar.littlestar.auth.service.AuthService;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request, HttpSession session) {
        User user = authService.signup(request, session);
        return ResponseEntity.ok(userService.toUserResponse(user));
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean exists = userService.checkUserName(username);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        User user = authService.login(request, session);
        return ResponseEntity.ok(userService.toUserResponse(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {

        authService.logout(session);
        return ResponseEntity.ok("로그아웃 완료");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        User user = authService.getUserInfo(session);
        return ResponseEntity.ok(userService.toUserResponse(user));
    }
}
