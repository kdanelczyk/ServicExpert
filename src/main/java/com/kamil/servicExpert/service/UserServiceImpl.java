package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Override
	public boolean existsById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found User with id = " + id);
		}
		return userRepository.existsById(id);
	}

	@Override
	public Optional<User> findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User type) {
		return userRepository.save(type);
	}

	@Override
	public User updateUser(Long id, User user) {
		User _user = findById(id).get();
		_user.setUsername(user.getUsername());
		_user.setName(user.getName());
		_user.setSurname(user.getSurname());
		_user.setUserPhoneNumber(user.getUserPhoneNumber());
		save(_user);
		return user;
	}

	@Override
	public void deleteById(Long id) {
		existsById(id);
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

}
