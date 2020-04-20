package com.pysch.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "players")
public class Player extends User {
	@NotBlank
	@Getter
	@Setter
	private String alais;
	@Getter
	@Setter
	private String pyschFaceURL;
	@Getter
	@Setter
	private String picURL;

	@ManyToMany(mappedBy = "players")
	@Getter
	@Setter
	@JsonIdentityReference
	Set<Game> games = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	@Getter
	@Setter
	@JsonManagedReference
	private Stat stat = new Stat();

	public Player() {

	}

	private Player(Builder builder) {
		setAlais(builder.alais);
		setEmail(builder.email);
		setSaltedHashedPassword(builder.saltedHashedPassword);
		setPyschFaceURL(builder.pyschFaceURL);
		setPicURL(builder.picURL);
	}

	public static final class Builder {
		private String pyschFaceURL;
		private String picURL;
		private @NotBlank String alais;
		private @NotBlank String saltedHashedPassword;
		private @Email @NotBlank String email;

		public Builder() {

		}

		public Builder picURl(String val) {
			this.picURL = val;
			return this;
		}

		public Builder alais(String val) {
			alais = val;
			return this;
		}

		public Builder email(@Email @NotBlank String email) {
			this.email = email;
			return this;
		}

		public Builder saltedHashedPassword(@NotBlank String val) {
			this.saltedHashedPassword = val;
			return this;
		}

		public Builder pyschFaceURL(String val) {
			this.pyschFaceURL = val;
			return this;
		}

		public Player build() {
			return new Player(this);
		}

	}

	public Game getCurrentGame() {
		// TODO Auto-generated method stub
		return null;
	}
}


