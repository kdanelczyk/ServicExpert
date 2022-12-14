package com.kamil.servicExpert.model.Type;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamil.servicExpert.model.Element.ElementSlim;

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
public class TypeGetDetails {
	
	@JsonProperty("type_name")
	private String nameOfType;
	
	@JsonProperty("elements")
	private List<ElementSlim> elements;
}
