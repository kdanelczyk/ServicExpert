package com.kamil.servicExpert.model.UsedElement;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
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
public class UsedElementDtoPost {
	
	@JsonProperty("element_name")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String nameOfElement;
	
	@JsonProperty("element_price")
	@NotNull(message = "price of element is required.")
	private BigDecimal priceOfElement;
	
}
