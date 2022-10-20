package com.kamil.servicExpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}