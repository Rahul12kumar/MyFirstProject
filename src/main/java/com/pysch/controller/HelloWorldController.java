package com.pysch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pysch.model.GameMode;
import com.pysch.model.Player;
import com.pysch.model.Question;
import com.pysch.repositories.PlayerRepository;
import com.pysch.repositories.QuestionRepository;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/")
	public String hello() {
		return "Hello, World";
	}

	@GetMapping("/questions")
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@GetMapping("/questions/{id}")
	public Question getQuestionsById(@PathVariable(name = "id") Long id) {
		return questionRepository.findById(id).orElseThrow(null);
	}

	@GetMapping("/players")
	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("/players/{id}")
	public Player getPlayersById(@PathVariable(name = "id") Long id) {
		return playerRepository.findById(id).orElseThrow(null);
	}

	@GetMapping("/populate")
	public String populateDB() {
		Player luffy = new Player.Builder().alais("D. luffy").email("luffy@interviewbit.com")
				.saltedHashedPassword("luffy").build();
		playerRepository.save(luffy);
		Player robin = new Player.Builder().alais("D. robin").email("robin@interviewbit.com")
				.saltedHashedPassword("robin").build();
		playerRepository.save(robin);

		questionRepository
				.save(new Question("What is most important Poneglyph", "Rio Poneglyph", GameMode.IS_THIS_A_FACT));
		
		
		return "populated";
	}
}






