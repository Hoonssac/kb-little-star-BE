package com.kbstar.littlestar.controller;

import com.kbstar.littlestar.domain.User;
import com.kbstar.littlestar.dto.SignupRequest;
import com.kbstar.littlestar.repository.UserRepository;
import com.kbstar.littlestar.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        User user = userService.signup(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean exists = userRepository.findByUsername(username).isPresent();
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");

        User user = userService.login(username, password);

        session.setAttribute("user", user); // 세션에 유저 저장
        System.out.println("세션 ID: " + session.getId());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션 만료
        return ResponseEntity.ok("로그아웃 완료");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(user);
    }
}
