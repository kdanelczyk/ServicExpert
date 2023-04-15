package com.kamil.servicExpert.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kamil.servicExpert.db.model.ERole;
import com.kamil.servicExpert.db.model.Role;
import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.payload.request.UserLoginRequest;
import com.kamil.servicExpert.payload.request.UserSignupRequest;
import com.kamil.servicExpert.repository.RoleRepository;
import com.kamil.servicExpert.security.jwt.JwtUtils;
import com.kamil.servicExpert.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
	
	UserService userService;
	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder;
	AuthenticationManager authenticationManager;
	JwtUtils jwtUtils;
	
	@Override
	public UserDetailsImpl authenticateUser(UserLoginRequest userLoginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return (UserDetailsImpl) authentication.getPrincipal();
	}
	
	@Override
	public void registerUser(UserSignupRequest userSignUpRequest) {
		User user = User.builder()
				.username(userSignUpRequest.getUsername())
				.email(userSignUpRequest.getEmail())
				.password(passwordEncoder.encode(userSignUpRequest.getPassword()))
				.name(userSignUpRequest.getName())
				.surname(userSignUpRequest.getSurname())
				.userPhoneNumber(userSignUpRequest.getUserPhoneNumber())
				.notes(new ArrayList<>())
				.repairs(new ArrayList<>())
				.build();

		List<String> rolesInString = userSignUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (rolesInString == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Catching Error: Role is not found. Please add a role to database."));
			roles.add(userRole);
		} else {
			rolesInString.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Catching Error: Role is not found. Please add a role to database."));
					roles.add(adminRole);

					break;
				case "mod":
					Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Catching Error: Role is not found. Please add a role to database."));
					roles.add(moderatorRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Catching Error: Role is not found. Please add a role to database."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userService.save(user);

	}
	
}
