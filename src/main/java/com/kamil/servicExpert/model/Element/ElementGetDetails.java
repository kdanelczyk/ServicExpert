package com.kamil.servicExpert.model.Element;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ElementGetDetails {
	
	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("critical_quantity")
	private int criticalQuantity;
	
	@JsonProperty("name_of_element")
	private String nameOfElement;
	
	@JsonProperty("price_of_element")
	private BigDecimal priceOfElement;
}
