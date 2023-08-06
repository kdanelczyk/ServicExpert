package com.kamil.servicExpert.payload.request;

import jakarta.validation.constraints.NotBlank;
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
public class UserLoginRequest {
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
}
