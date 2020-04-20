package com.pysch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name ="playeranswers")
public class PlayerAnswers extends Auditable{
	
	@NotNull
	@ManyToOne
	@Getter
	@Setter
	@JsonBackReference
	private Round round;
	
	public PlayerAnswers()
	{
		
	}
	public PlayerAnswers(@NotNull Round round, @NotNull Player player, @NotBlank String answer) {
		super();
		this.round = round;
		this.player = player;
		this.answer = answer;
	}
	@NotNull
	@ManyToOne
	@Getter
	@Setter
	@JsonIdentityReference
	private Player player;
	@NotBlank
	@Getter
	@Setter
	private String answer;
	
	

}


