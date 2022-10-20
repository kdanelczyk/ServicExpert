package com.kamil.servicExpert.model.Device;

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
public class DeviceGet {

	@JsonProperty("name_of_customer")
	private String nameOfCustomer;
	
	@JsonProperty("repaired")
	private boolean repaired;
}
