package com.pysch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="roles")
public class Role extends Auditable{
    @Column(unique=true)
    @NotBlank
    @Getter
    @Setter
	private String name;
    @Getter
    @Setter
    @NotBlank
	private String description ;
}

