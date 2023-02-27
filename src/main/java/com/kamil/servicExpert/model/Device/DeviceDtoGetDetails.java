package com.kamil.servicExpert.model.Device;

import java.util.Date;

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
public class DeviceDtoGetDetails {

	@JsonProperty("customer_phone_number")
	private long customerPhoneNumber;

	@JsonProperty("name_of_customer")
	private String nameOfCustomer;
	
	@JsonProperty("date_of_receipt")
	private Date dateOfReceipt;
	
	@JsonProperty("repaired")
	private boolean repaired;

}
