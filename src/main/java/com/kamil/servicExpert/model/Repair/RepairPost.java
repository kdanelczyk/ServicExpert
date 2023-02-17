package com.kamil.servicExpert.model.Repair;

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
public class RepairPost {

	@JsonProperty("cost")
	@NotNull(message = "cost is required.")
	private BigDecimal cost;

	@JsonProperty("note")
	@Size(min = 10, max = 300, message = "Note should have min 30 and max 300 characters.")
	private String note;

}
