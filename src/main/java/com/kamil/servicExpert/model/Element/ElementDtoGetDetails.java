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
public class ElementDtoGetDetails {
	
	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("critical_quantity")
	private int criticalQuantity;
	
	@JsonProperty("element_name")
	private String nameOfElement;
	
	@JsonProperty("element_price")
	private BigDecimal priceOfElement;
	
}
