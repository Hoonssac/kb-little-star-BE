package com.kbstar.littlestar.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.littlestar.quiz.service.QuizService;
import com.kbstar.littlestar.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {

	private final QuizService quizService;
	private final UserService userService;

	@GetMapping("")
	public ResponseEntity<?> getRandomQuiz() {
		return ResponseEntity.ok(quizService.getRandomQuiz());
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateAnsweredDate(HttpSession httpSession) {
		userService.updateAnsweredDate(httpSession);
		return ResponseEntity.ok("날짜 업데이트 완료.");
	}

	@PostMapping("/reward")
	public ResponseEntity<?> getMileage(HttpSession session, Boolean isCorrect) {
		quizService.addMileage(session, isCorrect);
		return ResponseEntity.ok("마일리지 지급 완료");
	}
}
