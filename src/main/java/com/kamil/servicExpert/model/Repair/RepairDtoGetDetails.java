package com.kamil.servicExpert.model.Repair;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.UsedElement.UsedElementDtoSlim;

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
public class RepairDtoGetDetails {
	
	@JsonProperty("dateCreated")
	private Date dateCreated;
	
	@JsonProperty("cost")
	private BigDecimal cost;

	@JsonProperty("note")
	private String note;

	@JsonProperty("device")
	private DeviceDtoGet device;
	
	@JsonProperty("used_elements")
	private List<UsedElementDtoSlim> usedElements;
	
}
