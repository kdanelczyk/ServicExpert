package com.kamil.servicExpert.security.service;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.payload.request.UserSignupRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface AuthService {
	
	public void registerUser(UserSignupRequest signUpRequest);

}
