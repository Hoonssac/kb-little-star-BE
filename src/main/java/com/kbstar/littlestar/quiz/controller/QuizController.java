package com.kbstar.littlestar.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.littlestar.quiz.dto.QuestionResponse;
import com.kbstar.littlestar.quiz.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {

	private final QuizService quizService;

	@GetMapping("")
	public ResponseEntity<QuestionResponse> getRandomQuiz() {
		return ResponseEntity.ok(quizService.getRandomQuiz());
	}
}
