package com.pysch.utils;

import javax.validation.constraints.NotNull;

import com.pysch.config.SpringConfiguration;
import com.pysch.model.EllenAnswer;
import com.pysch.model.GameMode;
import com.pysch.model.Question;
import com.pysch.repositories.EllenAnswerRepository;
import com.pysch.repositories.QuestionRepository;

@SuppressWarnings("static-access")
public class Utils {
	
	private static QuestionRepository questionRepository;
	
	private static EllenAnswerRepository ellenAnswerRepository;
	
	static
	{
		questionRepository = (QuestionRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("questionRepository");
		questionRepository = (QuestionRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("questionRepository");
	}

	public static Question getRandomQuestion(@NotNull GameMode gameMode) {
		return questionRepository.getRandomQuestion(gameMode).get();
	}

	public static EllenAnswer getRandomEllenAnser(Question questions) {
		ellenAnswerRepository.getRandomEllenAnswer(questions);
		return null;
	}

}
