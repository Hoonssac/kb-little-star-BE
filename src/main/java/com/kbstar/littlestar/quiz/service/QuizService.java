package com.kbstar.littlestar.quiz.service;

import org.springframework.stereotype.Service;

import com.kbstar.littlestar.quiz.domain.Question;
import com.kbstar.littlestar.quiz.dto.QuestionResponse;
import com.kbstar.littlestar.quiz.mapper.QuestionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuestionMapper questionMapper;

	public QuestionResponse getRandomQuiz() {
		Question question = questionMapper.getRandomQuestion();
		return new QuestionResponse(
			question.getQuestion(),
			question.getOptionsAsList(),
			question.getAnswerIndex(),
			question.getExplanation()
		);
	}
}
