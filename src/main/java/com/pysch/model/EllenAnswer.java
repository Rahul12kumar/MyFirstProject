package com.pysch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="ellenanswers")
public class EllenAnswer extends Auditable{
	@ManyToOne
	@NotNull
	@Getter
	@Setter
	@JsonBackReference
	private Question questions;
	
	@Getter
	@Setter
	private long votes = 0L;
	
	@NotBlank
	@Getter
	@Setter
	private String answer;

}


