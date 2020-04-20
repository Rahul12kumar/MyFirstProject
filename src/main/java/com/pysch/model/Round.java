package com.pysch.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pysch.exceptions.InvalidGameActionException;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "rounds")
public class Round extends Auditable {
	@ManyToOne
	@JsonBackReference
	@Getter
	@Setter
	private Game game;

	@NotNull
	@ManyToOne
	@Getter
	@Setter
	@JsonIdentityReference
	private Question questions;

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	@Getter
	@Setter
	private Map<Player, PlayerAnswers> playerAnswers = new HashMap<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	@Getter
	@Setter
	private Map<Player, PlayerAnswers> selectedAnswers = new HashMap<>();

	@ManyToOne
	@JsonIdentityReference
	@Getter
	@Setter
	private EllenAnswer ellenAnswer;

	@NotNull
	@Getter
	@Setter
	private int roundNumber;
	
	public Round()
	{
		
	}
	
	
	
	public void submitAnswer(Player player,String answer) throws InvalidGameActionException
	{
		if(playerAnswers.containsKey(player))
			throw new InvalidGameActionException("Player has already submitted the answer for the same round");
		
		for(PlayerAnswers existing:playerAnswers.values())
		{
			if(answer.equals(existing.getAnswer()))
				throw new InvalidGameActionException("Duplicate Answer");
		}
		playerAnswers.put(player,new PlayerAnswers(this,player,answer));
	}
	
	public boolean allAnswersSubmitted(int numplayers)
	{
		return playerAnswers.size()==numplayers;
	}
	public void selectAnswer(Player player,PlayerAnswers answer) throws InvalidGameActionException
	{
		if(selectedAnswers.containsKey(player))
			throw new InvalidGameActionException("Player has already submitted the answer for the same round");
		
		if(answer.getPlayer().equals(player))
			throw new InvalidGameActionException("Can't Select your own Answer");
		
		selectedAnswers.put(player, answer);
			
	}
	
	public boolean allAnswersSelected(int numplayers)
	{
		return selectedAnswers.size()==numplayers;
	}



	public Round(Game game, @NotNull Question questions, @NotNull int roundNumber) {
		super();
		this.game = game;
		this.questions = questions;
		this.roundNumber = roundNumber;
	}
	
}
