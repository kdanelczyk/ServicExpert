package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface UserService {
	
	public boolean existsById(Long id);

	public Optional<UserDtoGetDetails> findById(Long id);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);

	public List<UserDtoGet> findAll();

	public UserDtoGetDetails save(User user);
	
	public UserDtoGetDetails updateUser(Long id, User user);
	
	public void deleteById(Long id);

	public void deleteAll();
	
}
