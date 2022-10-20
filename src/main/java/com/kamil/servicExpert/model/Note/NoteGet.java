package com.kamil.servicExpert.model.Note;

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
public class NoteGet {

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("date_created")
	private Date dateCreated;
}
