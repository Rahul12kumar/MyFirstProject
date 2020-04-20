package com.pysch.auth;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pysch.exceptions.NoSuchUserException;
import com.pysch.model.User;
import com.pysch.repositories.UserRepository;

import lombok.SneakyThrows;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
	private UserRepository userRepository;
	
	@SneakyThrows
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByEmail(email);
		if(user.toString().isEmpty())
		{
			throw new NoSuchUserException("No user is registered with this"+email);
		}
		return new CustomUserDetails(user.get());
	}

}