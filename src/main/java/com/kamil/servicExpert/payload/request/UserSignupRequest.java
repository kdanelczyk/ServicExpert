package com.kamil.servicExpert.payload.request;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 30)
	private String password;

	@NotBlank
	@Column(name = "name")
	private String name;

	@NotBlank
	@Column(name = "surname")
	private String surname;

	@Column(name = "user_phone_number")
	private long userPhoneNumber;
    
    private List<String> role;
    
}