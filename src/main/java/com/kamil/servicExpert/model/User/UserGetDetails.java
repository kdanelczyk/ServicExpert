package com.kamil.servicExpert.model.User;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamil.servicExpert.model.Note.NoteGet;
import com.kamil.servicExpert.model.Repair.RepairGet;

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
public class UserGetDetails {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("user_phone_number")
	private long userPhoneNumber;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("repairs")
	private List<RepairGet> repairs;
	
	@JsonProperty("notes")
	private List<NoteGet> notes;

}
