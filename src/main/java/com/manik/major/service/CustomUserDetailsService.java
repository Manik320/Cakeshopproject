package com.manik.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manik.major.model.CustomUserDetail;
import com.manik.major.model.Users;
import com.manik.major.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users>users=userRepository.findUserByEmail(email);
		users.orElseThrow(()-> new UsernameNotFoundException("User is not found"));
		return users.map(CustomUserDetail::new).get();
		
		
	}
	

}
