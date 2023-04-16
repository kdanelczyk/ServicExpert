package com.kamil.servicExpert.model.Element;

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
public class ElementDtoGet {

	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("element_name")
	private String elementName;

}
