package com.pysch.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "questions")
public class Question extends Auditable {
	@NotNull
	@Getter
	@Setter
	private String question;
	@NotNull
	@Getter
	@Setter
	private String correctAnswer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questions")
	@JsonManagedReference
	@Getter
	@Setter
	private Set<EllenAnswer> ellenAnswer = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private GameMode gamemode;

	public Question() {

	}

	public Question(@NotNull String question, @NotNull String correctAnswer, GameMode gamemode) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.gamemode = gamemode;
	}

}



