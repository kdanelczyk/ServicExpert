package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface UserService {
	
	public boolean existsById(Long id);

	public Optional<User> findById(Long id);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);

	public List<User> findAll();

	public User save(User type);
	
	public User updateUser(Long id, User user);
	
	public void deleteById(Long id);

	public void deleteAll();
}
