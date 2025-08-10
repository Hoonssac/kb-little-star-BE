package com.kbstar.littlestar.quiz.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class Question {
	private Integer id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private Integer answerIndex;
	private String explanation;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public List<String> getOptionsAsList() {
		return Arrays.asList(option1, option2, option3, option4);
	}
}
