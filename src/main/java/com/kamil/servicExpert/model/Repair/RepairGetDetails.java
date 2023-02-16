package com.kamil.servicExpert.model.Repair;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamil.servicExpert.model.Device.DeviceGet;
import com.kamil.servicExpert.model.UsedElement.UsedElementSlim;

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
public class RepairGetDetails {
	
	@JsonProperty("dateCreated")
	private Date dateCreated;
	
	@JsonProperty("cost")
	private float cost;

	@JsonProperty("note")
	private String note;

	@JsonProperty("device")
	private DeviceGet device;
	
	@JsonProperty("usedElements")
	private List<UsedElementSlim> usedElements;
}
