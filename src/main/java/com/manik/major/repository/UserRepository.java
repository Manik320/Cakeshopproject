package com.manik.major.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manik.major.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users>findUserByEmail(String email);
	
}
