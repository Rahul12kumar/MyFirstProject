package com.pysch.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pysch.exceptions.InvalidGameActionException;
import com.pysch.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "games")
public class Game extends Auditable {
    
	@JsonIdentityReference
	@ManyToMany
	@Getter
	@Setter
	private Set<Player> players = new HashSet<>();

	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private GameMode gameMode;

	@OneToMany(mappedBy = "game",cascade=CascadeType.ALL)
	@Getter
	@Setter
	@JsonIgnore
	List<Round> rounds = new ArrayList<Round>();

	@Getter
	@Setter
	private boolean hasEllen = false;

	@Getter
	@Setter
	@NotNull
	@ManyToOne
	@JsonIgnore
	private Player leader;

	@ManyToMany(cascade = CascadeType.ALL)
	@Getter
	@Setter
	@JsonIgnore
	private Map<Player, Stat> playerStat = new HashMap<>();

	@Getter
	@Setter
	private int numRounds = 10;
	
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private GameStatus gameStatus;
	
	@ManyToMany
	@Getter
	@Setter
	@JsonIgnore
	private Set<Player> readyPlayers = new HashSet<>();
	
	public Game()
	{
		
	}

	public Game(@NotNull GameMode gameMode, boolean hasEllen, @NotNull Player leader, int numRounds) {
		super();
		this.gameMode = gameMode;
		this.hasEllen = hasEllen;
		this.leader = leader;
		this.numRounds = numRounds;
		this.players.add(leader);
	}
	
	public void addPlayer(Player player) throws InvalidGameActionException
	{
		if(!gameStatus.equals(GameStatus.PLAYERS_JOINING))
		{
			throw new InvalidGameActionException("can't join after the game has started");
		}
		players.add(player);
	}
	public void removePlayer(Player player) throws InvalidGameActionException
	{
		if(!players.contains(player))
		{
			throw new InvalidGameActionException("No such player was in the game");
		}
		players.remove(player);
		if(players.size()==0 || (players.size()==1 && !gameStatus.equals(GameStatus.PLAYERS_JOINING)))
			endGame();
		
	}
    public void startGame(Player player) throws InvalidGameActionException
    {
    	if(player.equals(leader))
    	{
    		throw new InvalidGameActionException("Only Leader can start the Game");
    	}
    	createNewRound();
    }
    
    public void submitAnswer(Player player,String answer) throws InvalidGameActionException
    {
    	if(answer.length()==0)
    		throw new InvalidGameActionException("Only Leader can start the Game");
    	if(!players.contains(player))
    		throw new InvalidGameActionException("No such player was in the game");
    	
    	if(!gameStatus.equals(GameStatus.SUBMITING_ANSWERS))
    		throw new InvalidGameActionException("Game is not accepting answer at present");
    	
    	Round currentRound = getCurrentRound();
    	currentRound.submitAnswer(player, answer);
    	if(currentRound.allAnswersSubmitted(players.size()))
    		gameStatus = GameStatus.SELECTING_ANSWERS;
    	
    }
    public void selectAnswer(Player player,PlayerAnswers playerAnswer) throws InvalidGameActionException
    {
    	if(!players.contains(player))
    		throw new InvalidGameActionException("No such player was in the game");
    	
    	if(!gameStatus.equals(GameStatus.SELECTING_ANSWERS))
    		throw new InvalidGameActionException("Game is not accepting answer at present");
    	
    	Round currentRound = getCurrentRound();
    	currentRound.selectAnswer(player, playerAnswer);
    	if(currentRound.allAnswersSelected(players.size()))
    		if(rounds.size()<numRounds)
    		gameStatus = GameStatus.WAITING_FOR_READY;
    		else
    			endGame();
    }
    
    public void playerIsReady(Player player) throws InvalidGameActionException
    {
    	if(!players.contains(player))
    		throw new InvalidGameActionException("No such player was in the game");
    	
    	if(!gameStatus.equals(GameStatus.WAITING_FOR_READY))
    		throw new InvalidGameActionException("Game is not accepting answer at present");
    	
    	readyPlayers.add(player);
    	if(readyPlayers.size()==players.size())
    		createNewRound();
    }
    public void playerIsNotReady(Player player) throws InvalidGameActionException
    {
    	if(!players.contains(player))
    		throw new InvalidGameActionException("No such player was in the game");
    	
    	if(!gameStatus.equals(GameStatus.WAITING_FOR_READY))
    		throw new InvalidGameActionException("Game is not accepting answer at present");
    	
    	readyPlayers.remove(player);
    }
    
    
	private Round getCurrentRound() throws InvalidGameActionException {
		// TODO Auto-generated method stub
		if(rounds.size()==0)
			throw new InvalidGameActionException("Game has not started Yet");
		return rounds.get(rounds.size()-1);
	}

	private void createNewRound() {
		gameStatus = GameStatus.SUBMITING_ANSWERS;
		Question questions = Utils.getRandomQuestion(gameMode);
		Round round = new Round(this,questions,rounds.size()+1);
		if(hasEllen)
			round.setEllenAnswer(Utils.getRandomEllenAnser(questions));
		rounds.add(round);
	}

	private void endGame() {
		gameStatus = GameStatus.ENDED;
	}
	
	public String getGame()
	{
		//TODO
		return "";
	}
}

