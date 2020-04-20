package com.pysch.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {
	@Email
	@NotBlank
	@Column(unique=true)
	@Getter
	@Setter
    private String email;
	@NotBlank
	@Getter
	@Setter
    private String saltedHashedPassword;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Getter
	@Setter
	Set<Role> roles = new HashSet<>();
	
	public User()
	{
		
	}
	
	public User(User user)
	{
		email = user.getEmail();
		saltedHashedPassword = user.getSaltedHashedPassword();
		roles = user.getRoles();
	}
}
