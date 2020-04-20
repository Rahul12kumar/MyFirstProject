package com.pysch.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Employee extends User{
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@NotBlank
	private String address;
	@Getter
	@Setter
	@NotBlank
	private String name;
	@Getter
	@Setter
	@NotBlank
	private String phoneNumber;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

