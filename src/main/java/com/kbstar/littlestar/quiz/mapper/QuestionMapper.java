package com.kbstar.littlestar.quiz.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kbstar.littlestar.quiz.domain.Question;

@Mapper
public interface QuestionMapper {
	Question getRandomQuestion();
}
