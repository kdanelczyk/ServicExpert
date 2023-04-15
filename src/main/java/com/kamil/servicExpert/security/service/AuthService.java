package com.kamil.servicExpert.security.service;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.payload.request.UserLoginRequest;
import com.kamil.servicExpert.payload.request.UserSignupRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface AuthService {
	
	public UserDetailsImpl authenticateUser(UserLoginRequest userLoginRequest);
	
	public void registerUser(UserSignupRequest userSignUpRequest);

}
