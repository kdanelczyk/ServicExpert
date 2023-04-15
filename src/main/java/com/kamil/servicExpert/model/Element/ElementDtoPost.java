package com.kamil.servicExpert.model.Element;

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
public class ElementDtoPost {

	@JsonProperty("quantity")
	@NotNull(message = "quantity is required.")
	private int quantity;

	@JsonProperty("critical_quantity")
	@NotNull(message = "criticalQuantity is required.")
	private int criticalQuantity;
	
	@JsonProperty("element_name")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String nameOfElement;
	
	@JsonProperty("element_price")
	@NotNull(message = "price is required.")
	private BigDecimal priceOfElement;
	
}
