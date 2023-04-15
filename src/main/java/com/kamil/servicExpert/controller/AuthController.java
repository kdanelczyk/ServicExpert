package com.kamil.servicExpert.controller;

import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.servicExpert.payload.request.UserLoginRequest;
import com.kamil.servicExpert.payload.request.UserSignupRequest;
import com.kamil.servicExpert.payload.response.UserInfoResponse;
import com.kamil.servicExpert.payload.response.MessageResponse;
import com.kamil.servicExpert.repository.RoleRepository;
import com.kamil.servicExpert.security.jwt.JwtUtils;
import com.kamil.servicExpert.security.service.AuthService;
import com.kamil.servicExpert.security.service.UserDetailsImpl;
import com.kamil.servicExpert.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	AuthService authService;
	UserService userService;
	RoleRepository roleRepository;
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
			UserDetailsImpl userDetails = authService.authenticateUser(userLoginRequest);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtUtils.generateJwtCookie(userDetails).toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getAuthorities()
						.stream()
						.map(item -> item.getAuthority())
						.collect(Collectors.toList())));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username is already in use. Please try another username."));
		}

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use. Please try another email."));
		}
		authService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MessageResponse("User registered correctly."));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtUtils.getCleanJwtCookie().toString())
				.body(new MessageResponse("User logged off."));
	}
	
}
