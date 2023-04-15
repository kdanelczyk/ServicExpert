package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.UserMapper;
import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;
import com.kamil.servicExpert.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private UserMapper userMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found User with id = " + id);
		}
		return userRepository.existsById(id);
	}

	@Override
	public Optional<UserDtoGetDetails> findById(Long id) {
		return Optional.of(userMapper.usersToUserGetDetails(userRepository.findById(id).get()));
		
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
	public List<UserDtoGet> findAll() {
		return userRepository.findAll()
				.stream()
				.map(userMapper::userToUserGet)
				.collect(Collectors.toList());
	}

	@Override
	public UserDtoGetDetails save(User user) {
		return userMapper.usersToUserGetDetails(userRepository.save(user));
	}

	@Override
	public UserDtoGetDetails updateUser(Long id, User user) {
		User userToUpdate = userRepository.findById(id).get();
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setName(user.getName());
		userToUpdate.setSurname(user.getSurname());
		userToUpdate.setUserPhoneNumber(user.getUserPhoneNumber());
		save(userToUpdate);
		return userMapper.usersToUserGetDetails(user);
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
