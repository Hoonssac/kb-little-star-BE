package com.kbstar.littlestar.quiz.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponse {
	private String question;
	private List<String> options;
	private Integer answerIndex;
	private String explanation;
}
