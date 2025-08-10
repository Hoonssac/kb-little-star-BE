package com.kbstar.littlestar.quiz.service;

import org.springframework.stereotype.Service;

import com.kbstar.littlestar.quiz.domain.Question;
import com.kbstar.littlestar.quiz.dto.QuestionResponse;
import com.kbstar.littlestar.quiz.mapper.QuestionMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuestionMapper questionMapper;
	private final UserService userService;

	public QuestionResponse getRandomQuiz() {
		Question question = questionMapper.getRandomQuestion();
		return new QuestionResponse(
			question.getQuestion(),
			question.getOptionsAsList(),
			question.getAnswerIndex(),
			question.getExplanation()
		);
	}

	public void addMileage(HttpSession session, Boolean isCorrect) {
		User user = (User)session.getAttribute("user");
		int reward = isCorrect ? 1000 : 500;
		userService.addMileage(user.getUsername(), reward);
	}
}
