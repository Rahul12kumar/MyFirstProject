package com.pysch.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pysch.model.Role;
import com.pysch.model.User;

@SuppressWarnings("serial")
public class CustomUserDetails extends User implements UserDetails {

	public CustomUserDetails(User user)
	{
		super(user);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Role> roles = super.getRoles();
		List<GrantedAuthority> authorties = new ArrayList<GrantedAuthority>();
		for(Role role:roles)
		{
			authorties.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(super.getSaltedHashedPassword());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}




