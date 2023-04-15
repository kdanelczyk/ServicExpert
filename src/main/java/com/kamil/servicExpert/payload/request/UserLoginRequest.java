package com.kamil.servicExpert.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserLoginRequest {
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
}
