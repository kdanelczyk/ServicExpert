package com.kamil.servicExpert.model.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TypeDtoPost {

	@JsonProperty("type_name")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String nameOfType;
	
}
