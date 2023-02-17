package com.kamil.servicExpert.model.Repair;

import java.math.BigDecimal;
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
public class RepairGet {
	
	@JsonProperty("date_created")
	private Date dateCreated;
	
	@JsonProperty("cost")
	private BigDecimal cost;

}
