package com.manik.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manik.major.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	

}
