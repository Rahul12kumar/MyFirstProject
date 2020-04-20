package com.pysch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pysch.exceptions.InvalidGameActionException;
import com.pysch.model.Player;
import com.pysch.repositories.PlayerRepository;

@Service
@RequestMapping("/play")
public class GamePlayController {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	
	@GetMapping("/submit-answer/{answer}")
	public void submitAnswer(Authentication authentication,@PathVariable(name="answer") String answer) throws InvalidGameActionException
	{
		Player player = playerRepository.findByEmail(authentication.getName()).orElseThrow(null);
		player.getCurrentGame().submitAnswer(player, answer);
	}

}
