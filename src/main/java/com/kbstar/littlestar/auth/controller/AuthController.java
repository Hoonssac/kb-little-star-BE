package com.kbstar.littlestar.auth.controller;

import com.kbstar.littlestar.auth.service.AuthService;
import com.kbstar.littlestar.user.entity.User;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.user.dto.UserResponse;
import com.kbstar.littlestar.user.repository.UserRepository;
import com.kbstar.littlestar.user.service.UserService;
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
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request, HttpSession session) {
        User user = authService.signup(request);
        session.setAttribute("user", user);
        return ResponseEntity.ok(new UserResponse());
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

        User user = authService.login(username, password);

        session.setAttribute("user", user); // 세션에 유저 저장
        System.out.println("세션 ID: " + session.getId());

        return ResponseEntity.ok(userService.toUserResponse(user));
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

        return ResponseEntity.ok(userService.toUserResponse(user));
    }
}
