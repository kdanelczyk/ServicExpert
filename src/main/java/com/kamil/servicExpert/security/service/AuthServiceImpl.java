package com.kamil.servicExpert.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kamil.servicExpert.db.model.ERole;
import com.kamil.servicExpert.db.model.Role;
import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.payload.request.UserSignupRequest;
import com.kamil.servicExpert.repository.RoleRepository;
import com.kamil.servicExpert.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
	
	UserService userService;
	RoleRepository roleRepository;
	PasswordEncoder encoder;
	
	@Override
	public void registerUser(UserSignupRequest signUpRequest) {
		User user = User.builder()
				.username(signUpRequest.getUsername())
				.email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword()))
				.name(signUpRequest.getName())
				.surname(signUpRequest.getSurname())
				.userPhoneNumber(signUpRequest.getUserPhoneNumber()).build();

		List<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userService.save(user);

	}
}
